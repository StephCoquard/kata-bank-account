package com.sco.kata.bankaccount.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Withdrawal(
    override val amount: Amount,
    override val date: LocalDateTime
): Operation {

    override val type: OperationTypeEnum
        get() = OperationTypeEnum.WITHDRAWAL

    init {
        if (amount.value < BigDecimal.ZERO) {
            throw NegativeAmountException()
        }
    }
}
