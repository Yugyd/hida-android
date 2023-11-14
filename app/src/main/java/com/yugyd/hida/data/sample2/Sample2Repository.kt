package com.yugyd.hida.data.sample2

import kotlinx.coroutines.flow.Flow

interface Sample2Repository {
    fun getData(): Flow<String>
}
