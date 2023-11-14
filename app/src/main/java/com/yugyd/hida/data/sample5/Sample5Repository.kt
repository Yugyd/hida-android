package com.yugyd.hida.data.sample5

import kotlinx.coroutines.flow.Flow

interface Sample5Repository {
    fun getData(): Flow<String>
}
