Feature: register functionallity
  As a user
  I want to register account in DEMOQA
  So that I have account in DEMOQA

  #TC_1
  #(BUG Minor) response body nama object userID berbeda dengan di open API userId =! userID
  Scenario: register with valid email and password
    Given I am set body request valid username "Nakama1" and password "@Password123"
    When I am hit endpoint register
    Then I am get status code register is 201
      And validate response body success
      And validate object username "Nakama1"

  #TC_2
  Scenario: register with an existing account
    Given I am set body request valid username "yogasaratoga" and password "@Password123"
    When I am hit endpoint register
    Then I am get status code register is 406
      And validate response body failed
      And validate object code "1204" and message "User exists!"

  #TC_3
  Scenario Outline: register with valid username and invalid password
    Given I am set body request valid username "Nakama2" and invalid password "<password>"
    When I am hit endpoint register
    Then I am get status code register is 400
      And validate response body failed
      And validate object code "1300" and message "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."
    Examples:
      | password |
      | @P12     |
      | Pass12   |
      | @pass12  |
      | @Pass    |
