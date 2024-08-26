package apiTestCases;

import helpers.GetUserInfoAPI;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.DataProviders;

public class GetUserInfoTest {

    SoftAssert softAssert = new SoftAssert();
    private static final Logger logger = LogManager.getLogger(GetUserInfoTest.class);


    @Test(dataProvider = "ProgData", dataProviderClass = DataProviders.class)
    public void getUserInfo(String Name, String Age) {
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
}
