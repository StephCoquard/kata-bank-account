Feature: Statement

  Scenario: Get the statement of an account with no operations
    Given There is an account with the operations
      | type       | amount |
    Then The statement contains the following statement items
      | type       | amount | balance |
