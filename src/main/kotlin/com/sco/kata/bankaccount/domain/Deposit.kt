package com.sco.kata.bankaccount.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Deposit(
    override val amount: Amount,
    override val date: LocalDateTime
) : Operation {

    override val type: OperationTypeEnum
        get() = OperationTypeEnum.DEPOSIT

    init {
        if (amount.value < BigDecimal.ZERO) {
            throw NegativeAmountException()
        }
    }
}
