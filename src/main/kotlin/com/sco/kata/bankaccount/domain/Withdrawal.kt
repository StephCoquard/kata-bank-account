package com.sco.kata.bankaccount.domain

import java.time.LocalDateTime

data class Withdrawal(
    val amount: Amount,
    val date: LocalDateTime
)
