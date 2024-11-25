package domain

import model.Expense
import model.ExpenseCategory

interface ExpenseRepository {

    fun getAll(): List<Expense>

    fun add(expense: Expense)

    fun delete(expense: Expense): List<Expense>

    fun edit(expense: Expense)

    fun getCategories(): List<ExpenseCategory>
}