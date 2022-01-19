package com.sco.kata.bankaccount.domain

data class Statement(private val items: MutableCollection<StatementItem> = ArrayList()) {

    fun getOrderedItems() = this.items.reversed()

    fun add(item: StatementItem) = items.add(item)
}