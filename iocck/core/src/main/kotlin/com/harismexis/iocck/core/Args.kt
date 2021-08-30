package com.harismexis.iocck.core

open class Args(val parameters: Array<out Any>) {

    inline operator fun <reified T> component1(): T = requireAt(0)
    inline operator fun <reified T> component2(): T = requireAt(1)
    inline operator fun <reified T> component3(): T = requireAt(2)
    inline operator fun <reified T> component4(): T = requireAt(3)
    inline operator fun <reified T> component5(): T = requireAt(4)

    inline fun <reified T> requireAt(index: Int) = parameters.elementAt(index) as? T
        ?: throw UnknownArgException()

    companion object {
        val EMPTY = Args(emptyArray())
        fun of(vararg parameters: Any) = Args(parameters)
    }
}

