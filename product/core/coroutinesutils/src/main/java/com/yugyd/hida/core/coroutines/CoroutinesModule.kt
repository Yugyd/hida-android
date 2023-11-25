package com.yugyd.hida.core.coroutines

import com.yugyd.coroutines.utils.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @Provides
    fun providesDispatchersProvider(): DispatchersProvider = DispatchersProviderImpl()
}
