package com.sco.kata.bankaccount.domain

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.LocalDateTime

class WithdrawalTest {

    @Test
    fun `Should throw exception if withdrawal initiated with -10`() {
        val exception = assertThrows<NegativeAmountException> {
            Withdrawal(Amount(BigDecimal.valueOf(-10)), LocalDateTime.now())
        }

        Assertions.assertThat(exception.message)
            .isEqualTo("Impossible to create an operation with a negative amount")
    }
}