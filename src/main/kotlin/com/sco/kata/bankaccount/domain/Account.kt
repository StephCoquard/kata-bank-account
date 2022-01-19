package com.sco.kata.bankaccount.domain

import java.math.BigDecimal

class Account() {

    var balance = Balance(BigDecimal.ZERO)
        private set

    fun deposit(deposit: Deposit) {
        this.balance = balance add deposit.amount
    }

    fun withdraw(withdrawal: Withdrawal) {
        this.balance = Balance(BigDecimal.ZERO)
    }
}