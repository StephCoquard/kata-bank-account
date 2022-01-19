package com.sco.kata.bankaccount.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.LocalDateTime

class BalanceTest {

    @Test
    fun `Should return a new balance with value 25 when adding an amount of 10 and previous balance is 15`() {
        val balance = Balance(BigDecimal.valueOf(15))

        val result = balance.add(Amount(BigDecimal.TEN))

        assertThat(result).isEqualTo(Balance(BigDecimal.valueOf(25)))
    }

    @Test
    fun `Should return a new balance with value 15 when subtracting an amount of 10 and previous balance is 25`() {
        val balance = Balance(BigDecimal.valueOf(25))

        val result = balance.subtract(Amount(BigDecimal.TEN))

        assertThat(result).isEqualTo(Balance(BigDecimal.valueOf(15)))
    }

    @Test
    fun `Should throw exception if subtract amount of 20 on a balance of 10`() {
        val depositAmount = Amount(BigDecimal.TEN)
        val withdrawalAmount = Amount(BigDecimal.valueOf(20))
        val depositDate = LocalDateTime.now()
        val withdrawalDate = depositDate.minusDays(1)
        val account = Account()
        account.deposit(Deposit(depositAmount, depositDate))
        val withdrawal = Withdrawal(withdrawalAmount, withdrawalDate)

        val exception = assertThrows<NegativeBalanceException> { account.withdraw(withdrawal) }

        assertThat(exception.message)
            .isEqualTo("Overdraft forbidden!")
    }
}