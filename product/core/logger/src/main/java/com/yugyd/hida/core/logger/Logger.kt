package com.yugyd.hida.core.logger

interface Logger {
    fun throwIfDebug(error: Throwable)
    fun log(msg: String)
    fun log(error: Throwable)
}
