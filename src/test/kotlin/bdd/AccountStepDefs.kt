package bdd

import com.sco.kata.bankaccount.domain.*
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
import java.math.BigDecimal
import java.time.LocalDateTime

class AccountStepDefs : En {

    private lateinit var account: Account

    init {
        Given("There is an account with the operations") { table: DataTable ->
            account = Account()

            table.asMaps().map {
                val type = it.getValue("type")
                val amount = it.getValue("amount").toBigDecimal()
                account.deposit(Deposit(Amount(amount), LocalDateTime.now()))
            }
        }

        When("A user deposits an amount of {bigdecimal}") { amount: BigDecimal ->
            val deposit = Deposit(Amount(amount), LocalDateTime.now())
            account.deposit(deposit)
        }

        Then("The account balance should be {bigdecimal}") { balance: BigDecimal ->
            assertThat(account.balance).isEqualTo(Balance(balance))
        }
    }
}