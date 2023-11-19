package com.yugyd.hida.core.toolbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(title: String) {
    TopAppBar(
        title = { Text(text = title) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ToolbarPreview() {
    MaterialTheme {
        TopAppBar(
            title = { Text(text = "Foo") }
        )
    }
}
