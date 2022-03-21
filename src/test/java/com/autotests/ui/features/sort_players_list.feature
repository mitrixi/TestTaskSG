Feature: Sort players list by particular column

  Scenario: Players list is sorted correctly
    Given players management page is open
    When sort players table by particular column
    Then players table has sorted