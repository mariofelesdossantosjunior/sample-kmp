package data

import com.expenseApp.db.AppDatabase
import data.dto.ExpenseDTO
import domain.ExpenseRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import model.Expense
import model.ExpenseCategory

private val BASE_URL = "http://192.168.122.1:8080"

class ExpenseRepositoryImpl(
    appDatabase: AppDatabase,
    private val httpClient: HttpClient
) : ExpenseRepository {

    private val queries = appDatabase.expensesDbQueries

    override suspend fun getAll(): List<Expense> {
        return when {
            queries.selectAll().executeAsList().isEmpty() -> {
                val response = httpClient.get(urlString = "$BASE_URL/expenses").body<List<ExpenseDTO>>()
                val expenses = response.map { it.mapTo() }
                expenses.forEach {
                    queries.insert(
                        amount = it.amount,
                        categoryName = it.category.name,
                        description = it.description
                    )
                }
                expenses
            }

            else -> {
                queries.selectAll().executeAsList().map {
                    Expense(
                        id = it.id,
                        amount = it.amount,
                        category = ExpenseCategory.valueOf(it.categoryName),
                        description = it.description
                    )
                }
            }
        }
    }

    override suspend fun add(expense: Expense) {
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }

        httpClient.post(urlString = "$BASE_URL/expenses") {
            contentType(ContentType.Application.Json)
            setBody(ExpenseDTO.mapFrom(expense))
        }
    }

    override suspend fun delete(expense: Expense): List<Expense> {
        queries.transaction {
            queries.delete(
                expense.id
            )
        }
        httpClient.delete(urlString = "$BASE_URL/expenses/${expense.id}")

        return getAll()
    }

    override suspend fun edit(expense: Expense) {
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }

        httpClient.post(urlString = "$BASE_URL/expenses/${expense.id}") {
            contentType(ContentType.Application.Json)
            setBody(ExpenseDTO.mapFrom(expense))
        }
    }

    override fun getCategories(): List<ExpenseCategory> {
        return queries.categories().executeAsList()
            .map {
                ExpenseCategory.valueOf(it)
            }
    }
}