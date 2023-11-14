package com.yugyd.hida.data.sample4

import kotlinx.coroutines.flow.Flow

interface Sample4Repository {
    fun getData(): Flow<String>
}
