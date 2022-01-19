package com.sco.kata.bankaccount.domain

import java.math.BigDecimal

data class Balance(val value: BigDecimal) {
    infix fun add(amount: Amount): Balance = Balance(this.value.plus(amount.value))
}
