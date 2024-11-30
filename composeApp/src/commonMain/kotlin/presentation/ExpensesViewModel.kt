package presentation

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ExpensesViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUIState())
    val uiState = _uiState.asStateFlow()

    private var allExpenses = mutableListOf<Expense>()

    init {
        getAllExpenses()
    }

    private fun updateExpenses() {
        viewModelScope.launch {
            allExpenses = repository.getAll().toMutableList()
            updateState()
        }
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
        repository.getAll()
        updateExpenses()
    }

    fun add(expense: Expense) {
        repository.add(expense)
        updateExpenses()
    }

    fun edit(expense: Expense) {
        repository.edit(expense)
        updateExpenses()
    }

    fun delete(expense: Expense) {
        repository.delete(expense)
        updateExpenses()
    }

    fun getExpenseByID(id: Long): Expense {
        return allExpenses.first { it.id == id }
    }

    fun getCategories(): List<ExpenseCategory> {
        return repository.getCategories()
    }
}