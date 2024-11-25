package presentation

import model.Expense

data class ExpensesUIState(
    val expenses: List<Expense> = emptyList(),
    val total: Double = 0.0
)
