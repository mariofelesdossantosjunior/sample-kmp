package org.example.project.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ui.AllExpensesHeader
import ui.ExpensesTotalHeader

@Preview(showBackground = true)
@Composable
private fun ExpensesTotalHeaderPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpensesTotalHeader(
            total = 100.0
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AllExpensesHeaderPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        AllExpensesHeader()
    }
}