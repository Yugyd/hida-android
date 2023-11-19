package com.yugyd.hida.ui.feature

import com.yugyd.coroutines.utils.DispatchersProvider
import com.yugyd.hida.core.componentui.BaseViewModel
import com.yugyd.hida.core.coroutines.onFailure
import com.yugyd.hida.core.coroutines.onSuccess
import com.yugyd.hida.core.logger.Logger
import com.yugyd.hida.domain.feature.MvvmGoogleUseCase
import com.yugyd.hida.ui.feature.FeatureView.Action
import com.yugyd.hida.ui.feature.FeatureView.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val useCase: MvvmGoogleUseCase,
    private val logger: Logger,
    private val dispatchersProvider: DispatchersProvider,
) : BaseViewModel<State, Action>(
    initialState = State(),
    logger = logger,
    dispatchersProvider = dispatchersProvider,
) {

    init {
        loadData()
    }

    private fun loadData() {
        screenState = screenState.copy(isLoading = true)

        vmScopeErrorHandled.launch {
            useCase()
                .onFailure {
                    processDataError(it)
                }
                .onSuccess {
                    screenState = screenState.copy(data = it)
                }
        }
    }

    private fun processData(data: String) {
        screenState = screenState.copy(
            isLoading = false,
            data = data,
        )
    }

    private fun processDataError(error: Throwable) {
        screenState = screenState.copy(
            isLoading = false,
            showErrorMessage = true,
        )
        logger.log(error)
    }

    override fun handleAction(action: Action) = when (action) {
        Action.OnButtonClicked -> onButtonClicked()

        Action.OnNavigationHandled -> {
            screenState = screenState.copy(navigationState = null)
        }

        Action.OnSnackbarDismissed -> {
            screenState = screenState.copy(showErrorMessage = false)
        }
    }

    private fun onButtonClicked() {
        screenState = screenState.copy(
            navigationState = State.NavigationState.NavigateToNext,
        )
    }
}