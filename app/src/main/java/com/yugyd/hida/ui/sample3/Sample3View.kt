package com.yugyd.hida.ui.sample3

interface Sample3View {

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val title: String = "",
    )
}