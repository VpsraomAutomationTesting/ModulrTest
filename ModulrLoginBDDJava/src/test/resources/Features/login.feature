# Author
# Date
# Description
Feature: As a Modulr customer user I want to be able to login successfully to the Modulr Customer Portal   So that I can manage my Modulr accounts

  @Tests1to4
  Scenario Outline: Check login is successful with valid credentials
    Given User is on login page
    When user enters <username> and <password>
    And clicks on sign in button
    Then User <navigated> to the account overview page

    Examples: 
      | username     | password     | navigated |
      | NoUSR        | Goodpwd      |         1 |
      | Goodusername | NoPWD        |         2 |
      | Incorrectpwd | Goodpwd      |         3 |
      | Goodusername | IncorrectPwd |         4 |
      | Goodusername | Goodpwd      |         5 |
