package com.sco.kata.bankaccount.domain

import java.math.BigDecimal

class Account() {

    var balance = Balance(BigDecimal.ZERO)
        private set

    fun deposit(deposit: Deposit) {
        this.balance = balance add deposit.amount
    }

    fun withdraw(withdrawal: Withdrawal) {
        if (withdrawal.amount.value > balance.value) {
            throw NegativeBalanceException()
        }
        this.balance = balance subtract withdrawal.amount
    }

    fun printStatement(printer: Printer) {
        printer.print(Statement())
    }
}