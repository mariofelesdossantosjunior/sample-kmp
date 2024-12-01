package com.mario.routes

import com.mario.data.ErrorResponse
import com.mario.data.Expense
import com.mario.data.expenses
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put

fun Route.expensesRouting() {

    get("/expenses") {
        if (expenses.isEmpty()) {
            call.respondText { "No expenses found" }
        } else {
            call.respond(status = HttpStatusCode.OK, expenses)
        }
    }

    get(path = "/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val isNotValidId = expenses.map { it.id }.none { it == id }

        if (id == null || isNotValidId) {
            return@get call.respond(
                status = HttpStatusCode.NotFound,
                message = ErrorResponse("Expense not found")
            )
        }

        val expense = expenses.first { it.id == id }

        call.respond(status = HttpStatusCode.OK, expense)
    }

    post(path = "/expenses") {
        val expense = call.receive<Expense>().copy(
            id = expenses.maxOf { it.id } + 1
        )

        expenses.add(expense)
        call.respond(status = HttpStatusCode.Created, expense)

    }

    put(path = "/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val isNotValidId = expenses.map { it.id }.none { it == id }

        if (id == null || isNotValidId) {
            return@put call.respond(
                status = HttpStatusCode.NotFound,
                message = ErrorResponse("Expense not found")
            )
        }

        val expense = call.receive<Expense>()
        val index = expenses.indexOfFirst { it.id == id }

        expenses[index] = expense.copy(
            id = id
        )

        call.respond(status = HttpStatusCode.Accepted, expense)
    }

    delete(path = "/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val isNotValidId = expenses.map { it.id }.none { it == id }

        if (id == null || isNotValidId) {
            return@delete call.respond(
                status = HttpStatusCode.NotFound,
                message = ErrorResponse("Expense not found")
            )
        }

        expenses.removeIf { it.id == id }

        call.respond(status = HttpStatusCode.Accepted, message = "Expense removed")
    }
}