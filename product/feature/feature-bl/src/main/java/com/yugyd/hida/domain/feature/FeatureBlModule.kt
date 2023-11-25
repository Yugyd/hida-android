package com.yugyd.hida.domain.feature

import com.yugyd.hida.domain.feature.data.MvvmGoogleRepository
import com.yugyd.hida.domain.feature.data.MvvmGoogleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FeatureBlModule {

    @Provides
    fun provideMvvmGoogleRepository(): MvvmGoogleRepository = MvvmGoogleRepositoryImpl()
}
