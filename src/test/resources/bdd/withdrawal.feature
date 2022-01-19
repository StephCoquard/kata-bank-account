Feature: Withdrawal

  Scenario: Withdraw an amount equal to the balance of the account
    Given There is an account with the operations
      | type    | amount |
      | DEPOSIT | 10     |
    When A user withdraws an amount of 10
    Then The account balance should be 0
