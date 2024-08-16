package apiTestCases;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utilities.ExcelUtils;

public class UserInfo {

	{
		baseURI = "https://api.agify.io/";

	}

	@Test(dataProvider = "ProgData")
	public void getUserInfo(String Name, String Age) {
		// Creating Json object to send data along with post request
		JSONObject requestparams = new JSONObject();
		requestparams.put("Name", Name);

		Response resp_prog_details = given().header("Content-Type", "application/json").queryParam("name", Name)
				.body(requestparams.toJSONString()).when().get().then().assertThat().statusCode(200).log().all()
				.extract().response();
		// Asserting the status code is success
		int statusCode = resp_prog_details.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("The response code is " + statusCode);
		String responseBody = resp_prog_details.getBody().asPrettyString();
		// Asserting the correct values are posted
		Assert.assertEquals(responseBody.contains(Name), true);
		Assert.assertEquals(responseBody.contains(Age), true);
	}

	@DataProvider(name = "ProgData")
	String[][] get_prog_data() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/apiTestCases/ProgData.xlsx";
		int rownum = ExcelUtils.getRowCount(path, "Sheet1");
		int colnum = ExcelUtils.getCellCount(path, "Sheet1", 1);
		String progdata[][] = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {
				progdata[i - 1][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return progdata;
	}
}
