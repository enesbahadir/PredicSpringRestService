Feature: Register - UserController

  Scenario Outline : Registration is successful with a valid username and valid password

    Given the predic application register page
    When the user fill register form with "<username>" and "<password>", push the "Register" button
    Then Registration process should be successful

    Examples:
      | username | password  |
      | username=bahadirenes | password=123456 |

