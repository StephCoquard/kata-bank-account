package com.sco.kata.bankaccount.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class AccountTest {

    @Test
    fun `Should have balance of 10 when deposit an amount of 10 on an empty account`() {
        val amount = Amount(BigDecimal.TEN)
        val date = LocalDateTime.now()
        val account = Account()
        val deposit = Deposit(amount, date)

        account.deposit(deposit)

        Assertions.assertThat(account.balance)
            .isEqualTo(Balance(BigDecimal.TEN))
    }
}