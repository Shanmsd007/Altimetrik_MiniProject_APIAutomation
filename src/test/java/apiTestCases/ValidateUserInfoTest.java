package apiTestCases;

import helpers.GetUserInfoAPI;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.UserDetails;
import utilities.DataProviders;

public class ValidateUserInfoTest {
	SoftAssert softAssert = new SoftAssert();
	private static final Logger logger = LogManager.getLogger(ValidateUserInfoTest.class);

	@Test(dataProvider = "ProgData", dataProviderClass = DataProviders.class)
	public void validateUserInfoTest(String Name, String Age) {

		try {
			logger.info("*****Getting the user information*****");
			Response response = GetUserInfoAPI.getUserInfo(Name);
			logger.info("*****Getting the status code*****");
			int statusCode = response.getStatusCode();
			logger.info("The response code is " + statusCode);
			logger.info("*****Validating the status code*****");
			softAssert.assertEquals(statusCode, 200);
			logger.info("*****Validating the user information*****");
			UserDetails userDetails = GetUserInfoAPI.ValidateUserInfo(Name);
			logger.info("*****Validating the user's Name*****");
			logger.info("Expected name value is :" +Name);
			logger.info("Actual name value is :" +userDetails.getName());
			softAssert.assertEquals(userDetails.getName(), Name);
			logger.info("*****Validating the user's Age*****");
			logger.info("Expected age value is :" +Age);
			logger.info("Actual age value is :" +userDetails.getAge());
			softAssert.assertEquals(userDetails.getAge(), Age);
		} catch (Exception e) {
			logger.info("Error in validating the user information" +e.getMessage() +e.getCause());
			throw new RuntimeException(e);
		} finally {
			softAssert.assertAll();
		}

	}
}
