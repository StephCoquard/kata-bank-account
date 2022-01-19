package com.sco.kata.bankaccount.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class StatementTest {

    @Test
    fun `Should return items in reversed order`() {
        val statement = Statement()
        statement.add(StatementItem.from(Deposit(Amount(BigDecimal.TEN), LocalDateTime.now()), Balance(BigDecimal.TEN)))
        statement.add(StatementItem.from(Deposit(Amount(BigDecimal.TEN), LocalDateTime.now()), Balance(BigDecimal.valueOf(20))))
        statement.add(StatementItem.from(Deposit(Amount(BigDecimal.TEN), LocalDateTime.now()), Balance(BigDecimal.valueOf(30))))

        assertThat(statement.getOrderedItems())
            .extracting(StatementItem::balance)
            .containsExactly(
                Tuple(Balance(BigDecimal.valueOf(30))),
                Tuple(Balance(BigDecimal.valueOf(20))),
                Tuple(Balance(BigDecimal.valueOf(10))))
    }
}