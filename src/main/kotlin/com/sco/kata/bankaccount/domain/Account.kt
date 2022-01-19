package com.sco.kata.bankaccount.domain

import java.math.BigDecimal

class Account() {

    var balance = Balance(BigDecimal.ZERO)
        private set

    fun deposit(deposit: Deposit) {
        this.balance = Balance(deposit.amount.value)
    }
}