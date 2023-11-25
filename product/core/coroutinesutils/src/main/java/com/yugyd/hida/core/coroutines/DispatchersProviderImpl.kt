package com.yugyd.hida.core.coroutines

import com.yugyd.coroutines.utils.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class DispatchersProviderImpl(
    override val io: CoroutineDispatcher = Dispatchers.IO,
    override val default: CoroutineDispatcher = Dispatchers.Default,
    override val main: CoroutineDispatcher = Dispatchers.Main.immediate,
) : DispatchersProvider
