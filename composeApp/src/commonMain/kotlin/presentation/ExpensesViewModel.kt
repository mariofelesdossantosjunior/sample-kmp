package presentation

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ExpensesViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUIState>(ExpensesUIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            try {
                val expenses = repository.getAll()
                _uiState.value = ExpensesUIState.Success(
                    expenses = expenses,
                    total = expenses.sumOf { it.amount }
                )
            } catch (ex: Exception) {
                _uiState.value = ExpensesUIState.Error(
                    message = ex.message ?: "Error loading expenses"
                )
            }
        }
    }

    private suspend fun updateExpenses() {
        try {
            val expenses = repository.getAll()
            _uiState.value = ExpensesUIState.Success(
                expenses = expenses,
                total = expenses.sumOf { it.amount }
            )
        } catch (ex: Exception) {
            _uiState.value = ExpensesUIState.Error(
                message = ex.message ?: "Error loading expenses"
            )
        }
    }

    fun add(expense: Expense) {
        viewModelScope.launch {
            repository.add(expense)
            updateExpenses()
        }
    }

    fun edit(expense: Expense) {
        viewModelScope.launch {
            repository.edit(expense)
            updateExpenses()
        }
    }

    fun delete(expense: Expense) {
        viewModelScope.launch {
            repository.delete(expense)
            updateExpenses()
        }
    }

    fun getExpenseByID(id: Long): Expense? {
        return (_uiState.value as? ExpensesUIState.Success)?.expenses?.first { it.id == id }
    }

    fun getCategories(): List<ExpenseCategory> {
        return repository.getCategories()
    }
}