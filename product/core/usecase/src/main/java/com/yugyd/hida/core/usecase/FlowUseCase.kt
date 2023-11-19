package com.yugyd.hida.core.usecase

import com.yugyd.coroutines.utils.DispatchersProvider
import com.yugyd.hida.core.coroutines.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<T, R>(
    private val dispatchersProvider: DispatchersProvider,
) {

    suspend operator fun invoke(
        params: T? = null
    ): Flow<Result<R>> = execute(params)
        .catch {
            emit(Result.failure(it))
        }
        .flowOn(dispatchersProvider.io)

    abstract fun execute(params: T?): Flow<Result<R>>
}
