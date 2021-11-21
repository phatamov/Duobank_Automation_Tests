Feature: API Login Tests

  @api
  Scenario: Login with valid credentials and Verify Status
    Given I add the headers "Accept" "application/json"
    When I POST "payloadFile" to "/login.php" path
    Then I verify the status code should be 200

  @api
  Scenario: Login with valid credentials using POJO Class and Verify Status
    Given I add the headers "Accept" "application/json"
    When I POST info from POJO class to "/login.php" path
    Then I verify the status code should be 200

  @api
  Scenario: Login and Verify Users
    Given I add the headers "Accept" "application/json" and "Authorization" "AdminAut"
    When I send a GET request to "getmortagage.php" endpoint
    Then the status code should be 200
    And The list should contains firstname "Thomas" and "Domingo"

  @api
  Scenario: Create a New User using Serialization
    Given I add the headers "Content-Type", "application/json" and "Accept", "application/json"
    And I create a fake credentials and request in a map
    When I POST newly created user
    Then I verify the status code should be not greater than 300
    And The success "message" should be "You have successfully registered."

  @api
  Scenario: Create a New User using POJO Class
    Given I add the headers "Content-Type", "application/json" and "Accept", "application/json"
    And I create a fake credentials using POJO class
    When I POST newly created user
    Then I verify the status code should be not greater than 300
    And The success "message" should be "You have successfully registered."

  @api
  Scenario: Create a New User and Verify Login
    Given I add the headers "Content-Type", "application/json" and "Accept", "application/json"
    And I create a fake credentials and request in a map
    When I POST newly created user
    Then I verify the status code should be not greater than 300
    And The success "message" should be "You have successfully registered."
    Given I have new user "email" and "password"
    When I add body and POST endpoint as "/login.php"
    Then I should be able to login and the status code should be 200

  @api
  Scenario Outline: Create a New User using wrong First Name or Last Name
    Given I add the headers "Content-Type", "application/json" and "Accept", "application/json"
    And I create a wrong credentials using wrong "<first_name>", "<last_name>", "<email>" and "<password>"
    When I POST newly created user
    Then I verify the status code should be not greater than 400
    And The success "message" should be "Please Fill in all Required Fields!"

    Examples:
      | first_name | last_name | email                  | password  |
      |            | Brown     | dan.brown@gmail.com    | ehgeg3322 |
      | Miki       |           | miki.moore@hotmail.com | 9997dhhd  |

  @api
  Scenario Outline: Create a New User using wrong Email format
    Given I add the headers "Content-Type", "application/json" and "Accept", "application/json"
    And I create a wrong credentials using wrong "<first_name>", "<last_name>", "<email>" and "<password>"
    When I POST newly created user
    Then I verify the status code should be not greater than 400
    And The success "message" should be "Invalid Email Address!"

    Examples:
      | first_name | last_name | email        | password  |
      | Ali        | Brown     | tteue@       | ehgeg3322 |
      | Miki       | Mendoza   | @hotmail.com | 9997dhhd  |

  @api
  Scenario: Login with invalid credentials and Verify Status
    Given I add the headers "Accept" "application/json"
    When I POST using POJO to "/login.php" path
    Then I verify the status code should be not greater than 400

  @api
  Scenario: Login with valid Email and an empty Password, then Verify Status and Message
    Given I add the headers "Accept" "application/json"
    When I POST using POJO with invalid pass to "/login.php" path
    Then I verify the status code should be not greater than 400
    And The success "message" should be "Please Fill in all Required Fields!"