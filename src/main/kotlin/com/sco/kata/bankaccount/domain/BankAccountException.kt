package com.sco.kata.bankaccount.domain

class NegativeAmountException(): Exception("Impossible to create an operation with a negative amount")

class NegativeBalanceException(): Exception("Overdraft forbidden!")