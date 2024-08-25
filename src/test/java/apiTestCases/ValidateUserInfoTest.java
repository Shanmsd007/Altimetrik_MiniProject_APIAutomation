package apiTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.GetUserInfoAPI;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import pojo.UserDetails;
import utilities.DataProviders;

public class ValidateUserInfoTest {
	SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "ProgData", dataProviderClass = DataProviders.class)
	public void validateUserInfoTest(String Name, String Age) {

		try {
			UserDetails userDetails = GetUserInfoAPI.ValidateUserInfo(Name);
			softAssert.assertEquals(userDetails.getName(), Name);
			softAssert.assertEquals(userDetails.getAge(), Age);
		} catch (Exception e) {
			System.out.println("Error in validating the user information" +e.getMessage() +e.getCause());
			throw new RuntimeException(e);
		} finally {
			softAssert.assertAll();
		}

	}
}
