
Feature: As a user, I should be able to login using login page.

  Background: Common steps for all scenarios
    Given I am on the homepage

   @login
  Scenario: Log in a with valid credentials

    When I log in with the valid credentials
    Then I can pass to the Application page
