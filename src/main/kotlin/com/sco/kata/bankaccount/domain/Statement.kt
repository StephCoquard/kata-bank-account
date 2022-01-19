package com.sco.kata.bankaccount.domain

data class Statement(private val items: MutableCollection<StatementItem> = ArrayList()) {

    fun getOrderedItems() = ArrayList<StatementItem>()
}