Feature: Personal Information page related features

  Background: Common steps for all scenarios
    Given I am in preapproval details page and click on the next button to move to personal info page


  Scenario: Check personal Information page text

    When I pass to personal info page
    Then I verify personal info page text


  Scenario: Fill up the Mortgage Application Personal Information page with valid credentials

    When I fill up all fields on the personal info page
    Then I can pass to the expenses page

    # bug
  Scenario: Fill up the Mortgage Application Personal Information page with invalid credentials
  as an empty space for First Name and Last Name

    When I add empty space " " for First Name and Last Name
    Then I can pass to the expenses page with a wrong name format

    # bug
  Scenario: Fill up the Mortgage Application Personal Information page with invalid credentials
  as a wrong date of birth

    When I fill up the application with a wrong date of birth "01/01/2222"
    Then I verify if I pass to the expenses page

    # bug
  Scenario: Fill up the Mortgage Application Personal Information page with invalid credentials
  as a wrong SSN

    When I fill up the application with a wrong SSN "0"
    Then I verify if I pass to the expenses page with wrong ssn

    # bug
  Scenario: Fill up the Mortgage Application Personal Information page with invalid credentials
  as a wrong cell number

    When I fill up the application with a wrong cell number "0"
    Then I verify if I pass to the expenses page with wrong number

    # bug
  Scenario: Fill up the Mortgage Application Personal Information page with invalid credentials
  as a wrong email format

    When I fill up the application with a wrong email "=@gmail.com"
    Then I verify if I pass to the expenses page with wrong email

    @app
  Scenario Outline: Fill up the Mortgage Application Personal Information page with table

    When I fill up the application using "<first name>", "<last name>" and "<email>"
    Then I verify if I pass to the expenses page with all data

    Examples:
      | first name | last name | email                  |
      | Dan        | Brown     | dan.brown@gmail.com    |
      | Miki       | Moore     | miki.moore@hotmail.com |
      | Alfred     | Bronze    | alfred@mail.org        |
      | Andrew     | Zidane    | az@25.co               |

