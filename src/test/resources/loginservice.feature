Feature: Authentication - UserController

  - Authentication is successful with a valid username and valid password
  - Authentication is unsuccessful with an invalid username and invalid password
  - Authentication is unsuccessful with a valid username but invalid password
  - Authentication is unsuccessful with a valid username and no password
  - Endpoints that require authentication cannot be accessed without it

  Scenario Outline: Authentication is successful with a valid username and valid password

    Given the predic application login page
    When the user fill login form with "<username>" and "<password>", push the "Login" button
    Then login process should be successful

    Examples:
      | username | password  |
      | username=enesbahadir | password=123456 |

  Scenario Outline: Authentication is unsuccessful with an invalid username and invalid password

    Given the predic application login page
    When the user fill login form with invalid "<username>" and invalid "<password>", push the "Login" button
    Then login process should be fail

    Examples:
      | requestBody                         |
      | username=mlauren&password=Password1 |
      | username=dwhite&password=Password1  |
      | username=cgrant&password=Password1  |
      | username=jwild&password=Password1   |

  Scenario Outline: Authentication is unsuccessful with a valid username but invalid password

    Given the predic application login page
    When the user fill login form with valid "<username>" and invalid "<password>", push the "Login" button
    Then login process should be fail

    Examples:
      | requestBody                           |
      | username=rjohnson&password=Password1  |
      | username=tsmith&password=Password1    |
      | username=jmcdonald&password=Password1 |
      | username=abrown&password=Password1    |
