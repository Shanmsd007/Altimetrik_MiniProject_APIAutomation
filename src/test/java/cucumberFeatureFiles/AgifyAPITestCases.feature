Author: Shanmugam(spandu@altimetrik.com)
Feature: Agify API test cases
  To verify that the user is able to get the user details and validate it using AGIFY services

  @getUserInfo @validateUserInfo
  Scenario Outline: To verify that the user is able to get the required user details and validate it
    When User hits the get AGIFY API service and acquire all the required user details by passing "<Name>" as the query parameter
    Then Verify that the user is able to validate the response body with the given data such as "<Name>" and "<Age>"

    Examples:
      | Name   | Age |
      | john   | 73  |
#      | tom    | 66  |
#      | jerry  | 75  |
#      | kevin  | 50  |
#      | amanda | 46  |
