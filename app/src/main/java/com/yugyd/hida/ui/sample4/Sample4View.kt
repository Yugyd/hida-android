package com.yugyd.hida.ui.sample4

interface Sample4View {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val title: String = "",
    )
}