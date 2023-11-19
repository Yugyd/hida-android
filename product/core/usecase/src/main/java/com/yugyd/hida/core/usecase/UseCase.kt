package com.yugyd.hida.core.usecase

import com.yugyd.coroutines.utils.DispatchersProvider
import com.yugyd.hida.core.coroutines.Result
import com.yugyd.hida.core.coroutines.result
import kotlinx.coroutines.withContext

abstract class UseCase<in T, R>(
    private val dispatchersProvider: DispatchersProvider,
) {

    suspend operator fun invoke(
        params: T? = null
    ): Result<R> = withContext(dispatchersProvider.io) {
        result {
            execute(params)
        }
    }

    abstract suspend fun execute(params: T?): R
}
