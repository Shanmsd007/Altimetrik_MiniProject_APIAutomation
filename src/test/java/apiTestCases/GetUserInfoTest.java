package apiTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.GetUserInfoAPI;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import pojo.UserDetails;
import utilities.DataProviders;

public class GetUserInfoTest {

	SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "ProgData", dataProviderClass = DataProviders.class)
	public void getUserInfo(String Name, String Age) {
		try {
			Response response = GetUserInfoAPI.getUserInfo(Name);
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			System.out.println("The response code is " + statusCode);
		} catch (Exception e) {
			System.out.println("Error in getting the user information" +e.getMessage() +e.getCause());
			throw new RuntimeException(e);
		} finally {
			softAssert.assertAll();
		}

	}
}
