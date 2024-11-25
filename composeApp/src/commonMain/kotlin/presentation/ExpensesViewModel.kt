package presentation

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ExpensesViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUIState())
    val uiState = _uiState.asStateFlow()

    private val allExpenses = repository.getAll()

    init {
        getAllExpenses()
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(
                expenses = allExpenses,
                total = allExpenses.sumOf { it.amount }
            )
        }
    }

    private fun getAllExpenses() {
        viewModelScope.launch {
            updateState()
        }
    }

    fun add(expense: Expense) {
        viewModelScope.launch {
            repository.add(expense)
            updateState()
        }
    }

    fun edit(expense: Expense) {
        viewModelScope.launch {
            repository.edit(expense)
            updateState()
        }
    }

    fun delete(expense: Expense) {
        viewModelScope.launch {
            repository.delete(expense)
            updateState()
        }
    }

    fun getExpenseByID(id: Long): Expense {
        return allExpenses.first { it.id == id }
    }
}