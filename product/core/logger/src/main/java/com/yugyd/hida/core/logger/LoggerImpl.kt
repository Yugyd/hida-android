package com.yugyd.hida.core.logger

// TODO Добавить Timber
internal class LoggerImpl : Logger {

    override fun throwIfDebug(error: Throwable) {
        println(error)
        println("test")
    }

    override fun log(msg: String) {
        println(msg)
    }

    override fun log(error: Throwable) {
        println(error)
    }
}
