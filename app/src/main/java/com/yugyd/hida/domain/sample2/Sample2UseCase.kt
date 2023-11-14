package com.yugyd.hida.domain.sample2

import com.yugyd.hida.data.sample2.Sample2Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class Sample2UseCase(
    private val sample2Repository: Sample2Repository,
) {

    operator fun invoke(): Flow<String> {
        return sample2Repository
            .getData()
            .flowOn(Dispatchers.IO)
    }
}
