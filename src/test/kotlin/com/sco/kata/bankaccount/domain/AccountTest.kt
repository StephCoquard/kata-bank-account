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

    @Test
    fun `Should have balance of 25 when deposit an amount of 15 on an account with a balance of 10`() {
        val date = LocalDateTime.now()
        val account = Account()
        account.deposit(Deposit(Amount(BigDecimal.TEN), date))

        account.deposit(Deposit(Amount(BigDecimal.valueOf(15)), date))

        Assertions.assertThat(account.balance)
            .isEqualTo(Balance(BigDecimal.valueOf(25)))
    }

    @Test
    fun `Should have balance of 0 when withdrawing an amout of 10 on an account with a balance of 10`() {
        val amount = Amount(BigDecimal.valueOf(10))
        val depositDate = LocalDateTime.now()
        val withdrawalDate = depositDate.minusDays(1)
        val account = Account()
        account.deposit(Deposit(amount, depositDate))
        val withdrawal = Withdrawal(amount, withdrawalDate)

        account.withdraw(withdrawal)

        Assertions.assertThat(account.balance)
            .isEqualTo(Balance(BigDecimal.ZERO))
    }

    @Test
    fun `Should have balance of 10 when withdrawing an amount of 10 on an account with a balance of 20`() {
        val depositAmount = Amount(BigDecimal.valueOf(20))
        val withdrawalAmount = Amount(BigDecimal.TEN)
        val depositDate = LocalDateTime.now()
        val withdrawalDate = depositDate.minusDays(1)
        val account = Account()
        account.deposit(Deposit(depositAmount, depositDate))
        val withdrawal = Withdrawal(withdrawalAmount, withdrawalDate)

        account.withdraw(withdrawal)

        Assertions.assertThat(account.balance)
            .isEqualTo(Balance(BigDecimal.TEN))
    }
}