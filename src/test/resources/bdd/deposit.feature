Feature: Deposit

  Scenario: Deposit money into an empty account
    Given There is an account with the operations
      | type | amount |
    When A user deposits an amount of 10
    Then The account balance should be 10
