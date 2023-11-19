package com.yugyd.hida.ui.feature

interface FeatureView {

    data class State(
        val isLoading: Boolean = false,
        val isWarning: Boolean = false,
        val data: String = "",
        val showErrorMessage: Boolean = false,
        val navigationState: NavigationState? = null,
    ) {

        sealed interface NavigationState {
            object NavigateToNext : NavigationState
        }
    }

    sealed interface Action {
        object OnButtonClicked : Action
        object OnSnackbarDismissed : Action
        object OnNavigationHandled : Action
    }
}