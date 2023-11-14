package com.yugyd.hida.ui.sample2

interface Sample2View {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val title: String = "",
    )
}