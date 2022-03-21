Feature: Authorize as admin

  Scenario: Authorization successful
    Given login page is open
    When admin credentials are entered
    And admin credentials submitted
    Then dashboard page has loaded