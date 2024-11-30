import androidx.compose.ui.window.ComposeUIViewController
import di.appModules
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin(){
    startKoin {
        modules(appModules)
    }.koin
}