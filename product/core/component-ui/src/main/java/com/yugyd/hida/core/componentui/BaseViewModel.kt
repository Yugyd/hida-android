package com.yugyd.hida.core.componentui

import androidx.lifecycle.ViewModel
import com.yugyd.coroutines.utils.DispatchersProvider
import com.yugyd.hida.core.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State : Any, Action : Any> protected constructor(
    dispatchersProvider: DispatchersProvider,
    private val logger: Logger,
    initialState: State
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        logger.throwIfDebug(throwable)
    }

    protected val vmScopeErrorHandled = CoroutineScope(
        SupervisorJob() + dispatchersProvider.main + exceptionHandler
    )

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    protected var screenState: State
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun onAction(action: Action) {
        logger.log(action.toString())

        handleAction(action)
    }

    protected abstract fun handleAction(action: Action)
}
