import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ExampleTest {

    @Test
    fun sampleTest() {
        //Given
        val x = 5
        val y = 10

        //When
        val result = x + y

        //Then
        assertEquals(15, result)
    }

    @Test
    fun expense_model_list_test() {
        //Given
        val expenses = mutableListOf<Expense>()

        val expense = Expense(
            id = 1,
            amount = 100.0,
            category = ExpenseCategory.CAR,
            description = "Combustible"
        )

        //When
        expenses.add(expense)

        //Then
        assertContains(expenses, expense)
    }
}