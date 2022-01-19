Feature: Withdrawal

  Scenario: Withdraw an amount equal to the balance of the account
    Given There is an account with the operations
      | type    | amount |
      | DEPOSIT | 10     |
    When A user withdraws an amount of 10
    Then The account balance should be 0

  Scenario: Withdraw an amount inferior to the balance of the account
    Given There is an account with the operations
      | type    | amount |
      | DEPOSIT | 25     |
    When A user withdraws an amount of 15
    Then The account balance should be 10

  Scenario: Withdraw a negative amount
    Given There is an account with the operations
      | type    | amount |
    When A user withdraws an amount of -10
    Then The account balance should be 0
    Then An error occurs
