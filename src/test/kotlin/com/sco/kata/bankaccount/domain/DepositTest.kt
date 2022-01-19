package com.sco.kata.bankaccount.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.LocalDateTime

class DepositTest {

    @Test
    fun `Should throw exception if deposit initiated with -10`() {
        val exception = assertThrows<NegativeAmountException> {
            Deposit(Amount(BigDecimal.valueOf(-10)), LocalDateTime.now())
        }

        assertThat(exception.message)
            .isEqualTo("Impossible to create an operation with a negative amount")
    }
}