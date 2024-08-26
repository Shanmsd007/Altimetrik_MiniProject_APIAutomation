package helpers;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.UserDetails;
import utilities.ExcelUtils;

public class GetUserInfoAPI {

	String path = System.getProperty("user.dir") + "/src/test/java/apiTestCases/ProgData.xlsx";
	ExcelUtils XL = new ExcelUtils(path);
	static CommonUtils utils = new CommonUtils();
	static Response UserInforesponse = null;
	static UserDetails userDetails = null;
	private static final Logger logger = LogManager.getLogger(GetUserInfoAPI.class);

	public static Response getUserInfo(String name) {
		try {
			UserInforesponse = given().spec(utils.requestSpecification()).queryParam("name", name).when().get().then()
					.spec(utils.ResponseSpecification()).log().all().extract().response();
		} catch (IOException e) {
			logger.info("Error in getting the userInfo" + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return UserInforesponse;

	}

	public static UserDetails ValidateUserInfo(String name) {

		try {
			userDetails = given().spec(utils.requestSpecification()).queryParam("name", name).when().get().then()
					.spec(utils.ResponseSpecification()).log().all().extract().response().as(UserDetails.class);
		} catch (IOException e) {
			logger.info("Error in validating the userInfo" + e.getMessage() + e.getCause());
			e.printStackTrace();
		}

		return userDetails;
	}
}
