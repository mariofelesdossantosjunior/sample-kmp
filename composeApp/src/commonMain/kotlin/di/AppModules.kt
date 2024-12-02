package di

import com.expenseApp.db.AppDatabase
import data.ExpenseRepositoryImpl
import domain.ExpenseRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import org.koin.dsl.module
import presentation.ExpensesViewModel
import io.ktor.serialization.kotlinx.json.json

fun appModules(
    appDatabase: AppDatabase
) = module {

    single<HttpClient> {
        HttpClient{
            install(ContentNegotiation){
                json()
            }
        }
    }

    single<ExpenseRepository> {
        ExpenseRepositoryImpl(
            appDatabase = appDatabase,
            httpClient = get()
        )
    }

    factory {
        ExpensesViewModel(get())
    }
}