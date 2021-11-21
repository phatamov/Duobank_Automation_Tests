Feature: Sign Up related features

  Background: Common steps for all scenarios
    Given I am in homepage and click on sign up

 @signup
  Scenario: Sign up a new user

    When I fill up the fields with the new user info
    Then I can pass to the mortgage application page
