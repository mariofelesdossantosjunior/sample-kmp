package data

import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepositoryImpl(
    private val expenseManager: ExpenseManager
) : ExpenseRepository {

    override fun getAll(): List<Expense> {
        return expenseManager.fakeExpenses
    }

    override fun add(expense: Expense) {
        expenseManager.add(expense)
    }

    override fun delete(expense: Expense): List<Expense> {
        return expenseManager.delete(expense)
    }

    override fun edit(expense: Expense) {
        expenseManager.edit(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManager.getAllCategories()
    }
}