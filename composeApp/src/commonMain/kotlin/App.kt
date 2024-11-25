import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.TitleTopBarTypes
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import navigator.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PreComposeApp {
        val colors = getColorsTheme()

        AppTheme {
            val navigator = rememberNavigator()
            val titleTopBar = getTitleTopBar(navigator)
            val isEditOrAddExpense = titleTopBar != TitleTopBarTypes.DASHBOARD.value

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        elevation = 0.dp,
                        title = {
                            Text(
                                text = "Dashboards",
                                fontSize = 25.sp,
                                color = colors.textColor
                            )
                        },
                        navigationIcon = {
                            if (isEditOrAddExpense) {
                                IconButton(
                                    onClick = {
                                        navigator.popBackStack()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(16.dp),
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        tint = colors.iconColor,
                                        contentDescription = "Icon Back"
                                    )
                                }
                            } else {
                                Icon(
                                    modifier = Modifier.padding(16.dp),
                                    imageVector = Icons.Default.Apps,
                                    tint = colors.iconColor,
                                    contentDescription = "Dashboard"
                                )
                            }
                        },
                        backgroundColor = colors.backgroundColor
                    )
                },
                floatingActionButton = {
                    if (!isEditOrAddExpense) {
                        FloatingActionButton(
                            modifier = Modifier.padding(8.dp),
                            shape = RoundedCornerShape(50),
                            backgroundColor = colors.iconColor,
                            contentColor = Color.White,
                            onClick = {
                                navigator.navigate(route = "/addExpense")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Expense"
                            )
                        }
                    }
                }
            ) {
                Navigation(navigator)
            }
        }
    }
}

@Composable
fun getTitleTopBar(navigator: Navigator): String {
    var titleTopBar = TitleTopBarTypes.DASHBOARD

    val isOnAddExpense = navigator.currentEntry
        .collectAsState(null)
        .value?.route?.route
        .equals("/addExpense/{id}")

    if (isOnAddExpense) {
        titleTopBar = TitleTopBarTypes.ADD_EXPENSE
    }

    val isOnEditExpense = navigator.currentEntry
        .collectAsState(null)
        .value?.path<Long>("id")

    isOnEditExpense?.let {
        titleTopBar = TitleTopBarTypes.EDIT_EXPENSE
    }

    return titleTopBar.value
}