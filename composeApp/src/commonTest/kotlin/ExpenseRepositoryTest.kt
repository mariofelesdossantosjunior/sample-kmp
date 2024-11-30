import data.ExpenseManager
import data.ExpenseRepositoryImpl
import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/*
class ExpenseRepositoryTest {

    private val expenseManager = ExpenseManager
    private val repository = ExpenseRepositoryImpl(
        appDatabase =
    )

    @Test
    fun expense_list_is_not_empty() {
        //Given
        val expenses = mutableListOf<Expense>()

        //When
        expenses.addAll(repository.getAll())

        //Then
        assertTrue(expenses.isNotEmpty())
    }

    @Test
    fun add_new_expense() {
        //Given
        val expenses = repository.getAll()

        //When
        val expense = Expense(
            amount = 100.0,
            category = ExpenseCategory.CAR,
            description = "Combustible"
        )

        repository.add(expense)

        //Then
        assertContains(expenses, expenses.find { it.id == 6L })
    }

    @Test
    fun edit_expense() {
        //Given
        val expenses = repository.getAll()

        //When
        val newExpense = 6L
        val expense = Expense(
            amount = 100.0,
            category = ExpenseCategory.CAR,
            description = "Combustible"
        )
        repository.add(expense)

        assertNotNull(expenses.find { it.id == newExpense })

        val updatedExpense = Expense(
            id = newExpense,
            amount = 200.0,
            category = ExpenseCategory.OTHER,
            description = "BlaBla"
        )

        repository.edit(updatedExpense)

        //Then
        val expenseListAfter = repository.getAll()
        assertEquals(updatedExpense, expenseListAfter.find { it.id == newExpense })
    }

    @Test
    fun get_all_categories() {
        //Given
        val categories = mutableListOf<ExpenseCategory>()

        //When
        categories.addAll(repository.getCategories())

        //Then
        assertTrue(categories.isNotEmpty())
    }
}
 */