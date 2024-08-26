package apiTestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.GetUserInfoAPI;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import pojo.UserDetails;
import utilities.DataProviders;

public class ValidateUserInfoTest {
	SoftAssert softAssert = new SoftAssert();
	private static final Logger logger = LogManager.getLogger(ValidateUserInfoTest.class);

	@Test(dataProvider = "ProgData", dataProviderClass = DataProviders.class)
	public void validateUserInfoTest(String Name, String Age) {

		try {
			logger.info("*****Validating the user information*****");
			UserDetails userDetails = GetUserInfoAPI.ValidateUserInfo(Name);
			logger.info("*****Validating the user's Name*****");
			softAssert.assertEquals(userDetails.getName(), Name);
			logger.info("*****Validating the user's Age*****");
			softAssert.assertEquals(userDetails.getAge(), Age);
		} catch (Exception e) {
			logger.info("Error in validating the user information" +e.getMessage() +e.getCause());
			throw new RuntimeException(e);
		} finally {
			softAssert.assertAll();
		}

	}
}
