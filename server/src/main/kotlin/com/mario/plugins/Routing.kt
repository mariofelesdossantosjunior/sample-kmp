package com.mario.plugins

import com.mario.routes.expensesRouting
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        expensesRouting()
    }
}