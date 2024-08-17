package helpers;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import io.restassured.response.Response;
import pojo.UserDetails;
import utilities.ExcelUtils;

public class GetUserInfoAPI {

	String path = System.getProperty("user.dir") + "/src/test/java/apiTestCases/ProgData.xlsx";
	ExcelUtils XL = new ExcelUtils(path);
	static CommonUtils utils = new CommonUtils();
	static Response UserInforesponse = null;
	static UserDetails userDetails = null;

	public static Response getUserInfo(String name) {
		try {
			UserInforesponse = given().spec(utils.requestSpecification()).queryParam("name", name).when().get().then()
					.spec(utils.ResponseSpecification()).log().all().extract().response();
		} catch (IOException e) {
			System.out.println("Error in getting the userInfo" + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return UserInforesponse;

	}

	public static UserDetails ValidateUserInfo(String name) {

		try {
			userDetails = given().spec(utils.requestSpecification()).queryParam("name", name).when().get().then()
					.spec(utils.ResponseSpecification()).log().all().extract().response().as(UserDetails.class);
		} catch (IOException e) {
			System.out.println("Error in validating the userInfo" + e.getMessage() + e.getCause());
			e.printStackTrace();
		}

		return userDetails;
	}
}
