Feature: Statement

  Scenario: Get the statement of an account with no operations
    Given There is an account with the operations
      | type       | amount |
    Then The statement contains the following statement items
      | type       | amount | balance |

  Scenario: Get the statement of the past operations on the account
    Given There is an account with the operations
      | type       | amount |
      | DEPOSIT    | 25     |
      | WITHDRAWAL | 10     |
      | DEPOSIT    | 30     |
      | DEPOSIT    | 5      |
      | WITHDRAWAL | 40     |
    Then The statement contains the following statement items
      | type       | amount | balance |
      | WITHDRAWAL | 40     | 10      |
      | DEPOSIT    | 5      | 50      |
      | DEPOSIT    | 30     | 45      |
      | WITHDRAWAL | 10     | 15      |
      | DEPOSIT    | 25     | 25      |