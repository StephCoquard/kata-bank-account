package com.sco.kata.bankaccount.domain

import java.math.BigDecimal

class Account() {

    var balance = Balance(BigDecimal.ZERO)
        private set

    private val statement = Statement()

    fun deposit(deposit: Deposit) {
        this.balance = balance add deposit.amount
        updateStatement(deposit)
    }

    fun withdraw(withdrawal: Withdrawal) {
        if (withdrawal.amount.value > balance.value) {
            throw NegativeBalanceException()
        }
        this.balance = balance subtract withdrawal.amount
        updateStatement(withdrawal)
    }

    fun printStatement(printer: Printer) {
        printer.print(this.statement)
    }

    private fun updateStatement(operation: Operation) =
        statement.add(StatementItem.from(operation, this.balance))
}