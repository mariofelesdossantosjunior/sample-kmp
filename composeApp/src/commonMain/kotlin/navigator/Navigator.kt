package navigator

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseManager
import data.ExpenseRepositoryImpl
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpensesViewModel
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {

    val colors = getColorsTheme()

    val viewModel = viewModel(ExpensesViewModel::class) {
        ExpensesViewModel(
            repository = ExpenseRepositoryImpl(
                expenseManager = ExpenseManager
            )
        )
    }

    NavHost(
        modifier = Modifier.background(color = colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ) {
        scene(route = "/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(
                uiState = uiState
            ) { expense ->
                navigator.navigate("/addExpense/${expense.id}")
            }
        }

        scene(route = "/addExpense/{id}") { backStackEntry ->
            val idFromPath = backStackEntry.path<Long>("id")
            val isAddExpense = idFromPath?.let { id ->
                viewModel.getExpenseByID(id = id)
            }

            // TODO: Add expense screen

        }
    }

}
