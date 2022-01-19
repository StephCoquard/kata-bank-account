package com.sco.kata.bankaccount.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

        assertThat(account.balance)
            .isEqualTo(Balance(BigDecimal.TEN))
    }

    @Test
    fun `Should have balance of 25 when deposit an amount of 15 on an account with a balance of 10`() {
        val date = LocalDateTime.now()
        val account = Account()
        account.deposit(Deposit(Amount(BigDecimal.TEN), date))

        account.deposit(Deposit(Amount(BigDecimal.valueOf(15)), date))

        assertThat(account.balance)
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

        assertThat(account.balance)
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

        assertThat(account.balance)
            .isEqualTo(Balance(BigDecimal.TEN))
    }

    @Test
    fun `Should throw exception when withdrawing an amount superior to the account balance`() {
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

    @Test
    fun `Should print no items when no operations in account`() {
        val account = Account()
        val printer = PrinterForTest();

        account.printStatement(printer)

        assertThat(printer.statementItems).isEmpty()
    }

    @Test
    fun `Should print items in reversed order when requesting printing`() {
        val account = Account()
        val deposit1 = Deposit(Amount(BigDecimal.TEN), LocalDateTime.of(2022, 1, 1, 0, 0, 0))
        val deposit2 = Deposit(Amount(BigDecimal.valueOf(15)), LocalDateTime.of(2022, 1, 2, 0, 0, 0))
        val withdrawal = Withdrawal(Amount(BigDecimal.valueOf(8)), LocalDateTime.of(2022, 1, 3, 0, 0, 0))
        account.deposit(deposit1);
        account.deposit(deposit2);
        account.withdraw(withdrawal);
        val printer = PrinterForTest()

        account.printStatement(printer)

        assertThat(printer.statementItems)
            .extracting(
                StatementItem::operationType,
                StatementItem::operationAmount,
                StatementItem::operationDate,
                StatementItem::balance
            )
            .containsExactly(
                Tuple.tuple(withdrawal.type, withdrawal.amount, withdrawal.date, Balance(BigDecimal.valueOf(17))),
                Tuple.tuple(deposit2.type, deposit2.amount, deposit2.date, Balance(BigDecimal.valueOf(25))),
                Tuple.tuple(deposit1.type, deposit1.amount, deposit1.date, Balance(BigDecimal.TEN))
            )
    }
}