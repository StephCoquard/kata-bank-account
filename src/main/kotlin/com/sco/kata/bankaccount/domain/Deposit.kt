package com.sco.kata.bankaccount.domain

import java.time.LocalDateTime

data class Deposit(
    val amount: Amount,
    val date: LocalDateTime
)
