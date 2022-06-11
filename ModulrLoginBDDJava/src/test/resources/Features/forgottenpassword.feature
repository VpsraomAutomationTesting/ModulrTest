@Test5
Feature: The user should have the ability to reset their password should they be unable to remember
  their password which should take them to a screen that allows them to request a reset 
  password email

  Scenario: User should be able to reset password from Forgotten password link
    Given User is provided with Forgotten password Link
    When Clicks on the link
    Then Navigates to reset password page
    Then Entering username
    And Clicks on Request a reset button
    Then Navigates to Email Sent page
