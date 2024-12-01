package com.mario.data

import kotlinx.serialization.Serializable

@Serializable
data class Expense(
    val id: Long = expenses.size.toLong(),
    val amount: Double,
    val category: String,
    val description: String
)