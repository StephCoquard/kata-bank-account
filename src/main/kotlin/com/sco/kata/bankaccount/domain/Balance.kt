package com.sco.kata.bankaccount.domain

import java.math.BigDecimal

data class Balance(val value: BigDecimal) {
    infix fun add(amount: Amount): Balance = Balance(this.value.plus(amount.value))

    infix fun subtract(amount: Amount): Balance {
        if (amount.value > this.value) {
            throw NegativeBalanceException()
        }
        return Balance(this.value.minus(amount.value))
    }
}
