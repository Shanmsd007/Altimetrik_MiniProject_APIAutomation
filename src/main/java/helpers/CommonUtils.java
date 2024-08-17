package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CommonUtils {

	public static RequestSpecification reqSpec;
 
	public static RequestSpecification requestSpecification() throws IOException {
		if (reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("APILogs.txt"));
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURI"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return reqSpec;
		}
		return reqSpec;
	}

	public static ResponseSpecification resSpec;

	public ResponseSpecification ResponseSpecification() throws IOException {
		if (resSpec == null) {
			resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			return resSpec;
		}
		return resSpec;
	}

	public static String getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		String filepath = System.getProperty("user.dir") + "/src/test/resources/global.properties";
		FileInputStream file = new FileInputStream(filepath);
		prop.load(file);
		return prop.getProperty(key);
	}

	public String getJsonNodeValue(Response res, String jsonPath) {
		JsonPath jsp = new JsonPath(res.asString());
		return jsp.get(jsonPath).toString();
	}

}
