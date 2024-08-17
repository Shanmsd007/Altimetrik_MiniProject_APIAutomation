package apiTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.GetUserInfoAPI;
import io.restassured.response.Response;
import pojo.UserDetails;
import utilities.DataProviders;

public class GetUserInfoTest {

	@Test(dataProvider = "ProgData", dataProviderClass = DataProviders.class)
	public void getUserInfo(String Name, String Age) {
		Response response = GetUserInfoAPI.getUserInfo(Name);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("The response code is " + statusCode);

	}
}
