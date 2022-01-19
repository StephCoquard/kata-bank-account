package com.sco.kata.bankaccount.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Deposit(
    val amount: Amount,
    val date: LocalDateTime
) {
    init {
        if (amount.value < BigDecimal.ZERO) {
            throw NegativeAmountException()
        }
    }
}
