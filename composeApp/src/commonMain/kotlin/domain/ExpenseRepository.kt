package domain

import model.Expense
import model.ExpenseCategory

interface ExpenseRepository {

    suspend fun getAll(): List<Expense>

    suspend fun add(expense: Expense)

    suspend fun delete(expense: Expense): List<Expense>

    suspend fun edit(expense: Expense)

    fun getCategories(): List<ExpenseCategory>
}