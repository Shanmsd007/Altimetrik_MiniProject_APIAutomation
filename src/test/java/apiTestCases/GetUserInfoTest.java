package apiTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.GetUserInfoAPI;
import pojo.UserDetails;
import utilities.DataProviders;

public class GetUserInfoTest {

	@Test(dataProvider = "ProgData", dataProviderClass = DataProviders.class)
	public void validateUserInfoTest(String Name, String Age) {

		UserDetails userDetails = GetUserInfoAPI.ValidateUserInfo(Name);
		Assert.assertEquals(userDetails.getName(), Name);
		Assert.assertEquals(userDetails.getAge(), Age);

	}
}
