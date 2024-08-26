package stepDefinitionFiles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import helpers.GetUserInfoAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.UserDetails;

public class MyStepDefinition {

	SoftAssert softAssert = new SoftAssert();
	private static final Logger logger = LogManager.getLogger(MyStepDefinition.class);

	@When("User hits the get AGIFY API service and acquire all the required user details by passing {string} as the query parameter")
	public void user_hits_the_get_agify_api_service_and_acquire_all_the_required_user_details_by_passing_as_the_query_parameter(
			String Name) {
		try {
			logger.info("*****Getting the user information*****");
			Response response = GetUserInfoAPI.getUserInfo(Name);
			logger.info("*****Getting the status code*****");
			int statusCode = response.getStatusCode();
			logger.info("*****Validating the status code*****");
			softAssert.assertEquals(statusCode, 200);
			logger.info("The response code is " + statusCode);
		} catch (Exception e) {
			logger.info("Error in getting the user information" + e.getMessage() + e.getCause());
			throw new RuntimeException(e);
		} finally {
			softAssert.assertAll();
		}
	}
	
	@Then("Verify that the user is able to validate the response body with the given data such as {string} and {string}")
	public void verify_that_the_user_is_able_to_validate_the_response_body_with_the_given_data_such_as_and(
			String Name, String Age) {
		try {
			logger.info("*****Validating the user information*****");
			UserDetails userDetails = GetUserInfoAPI.ValidateUserInfo(Name);
			logger.info("*****Validating the user's Name*****");
			softAssert.assertEquals(userDetails.getName(), Name);
			logger.info("*****Validating the user's Age*****");
			softAssert.assertEquals(userDetails.getAge(), Age);
		} catch (Exception e) {
			logger.info("Error in validating the user information" + e.getMessage() + e.getCause());
			throw new RuntimeException(e);
		} finally {
			softAssert.assertAll();
		}
	}

}
