package navigator

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import presentation.ExpensesViewModel
import ui.ExpensesDetailScreen
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {

    val colors = getColorsTheme()

    val viewModel = koinViewModel(ExpensesViewModel::class)

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

        scene(route = "/addExpense/{id}?") { backStackEntry ->
            val idFromPath = backStackEntry.path<Long>("id")
            val expenseToEditOrAdd = idFromPath?.let { id ->
                viewModel.getExpenseByID(id = id)
            }

            ExpensesDetailScreen(
                expenseToEdit = expenseToEditOrAdd,
                categories = viewModel.getCategories(),
                addExpenseAndNavigateBack = { expense ->
                    if (expenseToEditOrAdd == null) {
                        viewModel.add(expense)
                    } else {
                        viewModel.edit(expense)
                    }
                    navigator.popBackStack()
                }
            )
        }
    }

}
