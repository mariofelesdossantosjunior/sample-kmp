package data.dto

import kotlinx.serialization.Serializable
import model.Expense
import model.ExpenseCategory

@Serializable
data class ExpenseDTO(
    val id: Long,
    val amount: Double,
    val category: String,
    val description: String
) {
    fun mapTo() = Expense(
        id = this.id,
        amount = this.amount,
        category = ExpenseCategory.valueOf(this.category),
        description = this.description
    )

    companion object {
        fun mapFrom(model: Expense): ExpenseDTO {
            return ExpenseDTO(
                id = model.id,
                amount = model.amount,
                category = model.category.name,
                description = model.description
            )
        }
    }
}