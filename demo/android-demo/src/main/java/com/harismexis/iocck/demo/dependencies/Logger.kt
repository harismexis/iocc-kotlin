package com.harismexis.iocck.demo.dependencies

class Logger {

    fun log() {
        print("Logger hash code: ${hashCode()}")
    }

    override fun toString(): String {
        return "Logger hash code: ${hashCode()}"
    }

}
