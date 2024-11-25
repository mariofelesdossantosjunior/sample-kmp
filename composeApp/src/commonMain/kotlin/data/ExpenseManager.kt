package data

import model.Expense
import model.ExpenseCategory

object ExpenseManager {

    private var currentId = 1L

    val fakeExpenses = mutableListOf(
        Expense(
            id = currentId++,
            amount = 100.0,
            category = ExpenseCategory.HOUSE,
            description = "Clean house"
        ),
        Expense(
            id = currentId++,
            amount = 30.0,
            category = ExpenseCategory.COFFEE,
            description = "Coffee"
        ),
        Expense(
            id = currentId++,
            amount = 40.0,
            category = ExpenseCategory.SNACKS,
            description = "Snacks"
        ),
        Expense(
            id = currentId++,
            amount = 120.0,
            category = ExpenseCategory.PARTY,
            description = "Weekend party"
        ),
        Expense(
            id = currentId++,
            amount = 140.0,
            category = ExpenseCategory.OTHER,
            description = "Services"
        )
    )

    fun add(expense: Expense) {
        fakeExpenses.add(
            expense.copy(
                id = currentId++
            )
        )
    }

    fun delete(expense: Expense): List<Expense> {
        fakeExpenses.remove(expense)
        return fakeExpenses
    }

    fun edit(expense: Expense) {
        val index = fakeExpenses.indexOfFirst {
            it.id == expense.id
        }

        if (index != -1) {
            fakeExpenses[index] = fakeExpenses[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }

    fun getAllCategories(): List<ExpenseCategory> {
        return ExpenseCategory.entries
    }
}