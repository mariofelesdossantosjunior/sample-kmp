import androidx.compose.ui.window.ComposeUIViewController
import com.expenseApp.db.AppDatabase
import data.DatabaseDriverFactory
import di.appModules
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(
            appModules(
                appDatabase = AppDatabase.invoke(
                    DatabaseDriverFactory()
                        .createDriver()
                )
            )
        )
    }.koin
}