package org.example.project.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import data.ExpenseManager
import presentation.ExpensesUIState
import ui.AllExpensesHeader
import ui.ExpenseAmount
import ui.ExpensesItem
import ui.ExpensesScreen
import ui.ExpensesTotalHeader

@Preview(showBackground = true)
@Composable
private fun ExpensesTotalHeaderPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpensesTotalHeader(
            total = 100.0
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AllExpensesHeaderPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        AllExpensesHeader()
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseItem() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpensesItem(
            expense = ExpenseManager.fakeExpenses.first(),
            onExpenseClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUIState.Success(
            expenses = ExpenseManager.fakeExpenses,
            total = 100.0
        ),
        onExpenseClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpenseAmountPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpenseAmount(
           priceContent = 0.0,
            onPriceChange = {},
            keyboardController = null
        )
    }
}