package com.sco.kata.bankaccount.domain

internal class PrinterForTest : Printer {
    lateinit var statementItems: List<StatementItem>

    override fun print(statement: Statement) {
        this.statementItems = statement.getOrderedItems()
    }
}