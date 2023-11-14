package com.yugyd.hida.data.sample3

import kotlinx.coroutines.flow.Flow

interface Sample3Repository {
    fun getData(): Flow<String>
}
