package presentation

import model.Expense

sealed class ExpensesUIState {
    data object Loading : ExpensesUIState()
    data class Success(val expenses: List<Expense>, val total: Double) : ExpensesUIState()
    data class Error(val message: String) : ExpensesUIState()
}