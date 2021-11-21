Feature: Mortgage Application Preapproval Details page related features

  Background: Common steps for all scenarios
    Given I am in dashboard page and click on Mortgage App link


  Scenario: Fill up the Mortgage Application Preapproval Details page with valid credentials

    When I fill up all fields on the preapproval details page
    Then I can pass to the personal information page


  Scenario: Feel up the Mortgage Application Preapproval Details page and check calculated loan amount

    When I fill up necessary fields on the preapproval details page
    Then I can check calculated loan amount credibility


  Scenario: Fill up the Mortgage Application Preapproval Details page and check the percentage of loan amount

    When I fill up necessary fields on the preapproval details page to check percentage
    Then I can check calculated loan amount percentage

  # bug
  Scenario: Fill up the Mortgage Application Preapproval Details page with invalid credentials for a realtor information

    When I fill up all fields on the preapproval details page using " " as a realtor information
    Then I can pass to the personal information page and verify it
