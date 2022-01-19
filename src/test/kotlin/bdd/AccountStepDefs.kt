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
    private lateinit var exceptionHandler: ExceptionHandler

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
            exceptionHandler = ExceptionHandler()

            try {
                val deposit = Deposit(Amount(amount), LocalDateTime.now())
                account.deposit(deposit)
            } catch (e: Exception) {
                exceptionHandler.add(e)
            }
        }

        When("A user withdraws an amount of {bigdecimal}") { amount: BigDecimal ->
            exceptionHandler = ExceptionHandler()

            try {
                val withdrawal = Withdrawal(Amount(amount), LocalDateTime.now())
                account.withdraw(withdrawal)
            } catch (e: Exception) {
                exceptionHandler.add(e)
            }
        }

        Then("The account balance should be {bigdecimal}") { balance: BigDecimal ->
            assertThat(account.balance).isEqualTo(Balance(balance))
        }

        Then("An error occurs") {
            assertThat(exceptionHandler.getExceptions().isNotEmpty())
        }

        Then("The statement contains the following statement items") { table: DataTable ->
            val expected: List<Tuple> = table.asMaps().map {
                Tuple(
                    OperationTypeEnum.valueOf(it.getValue("type")),
                    Amount(it.getValue("amount").toBigDecimal()),
                    Balance(it.getValue("balance").toBigDecimal())
                )
            }
            val printer = PrinterForTest()

            account.printStatement(printer)

            assertThat(printer.statementItems).extracting(
                StatementItem::operationType,
                StatementItem::operationAmount,
                StatementItem::balance)
                .isEqualTo(expected)
        }
    }
}