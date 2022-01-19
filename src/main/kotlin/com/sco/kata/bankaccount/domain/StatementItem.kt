package com.sco.kata.bankaccount.domain

import java.time.LocalDateTime

data class StatementItem constructor(
    val operationType: OperationTypeEnum,
    val operationAmount: Amount,
    val operationDate: LocalDateTime,
    val balance: Balance
) {
    companion object {
        fun from(operation: Operation, balance: Balance): StatementItem =
            StatementItem(operation.type, operation.amount, operation.date, balance)
    }
}