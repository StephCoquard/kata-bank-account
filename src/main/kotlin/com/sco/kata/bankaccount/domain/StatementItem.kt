package com.sco.kata.bankaccount.domain

import java.time.LocalDateTime

data class StatementItem constructor(
    val operationType: OperationTypeEnum,
    val operationAmount: Amount,
    val operationDate: LocalDateTime,
    val balance: Balance
)