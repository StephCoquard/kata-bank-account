package com.sco.kata.bankaccount.domain

import java.time.LocalDateTime

interface Operation {
    val type: OperationTypeEnum
    val amount: Amount
    val date: LocalDateTime
}