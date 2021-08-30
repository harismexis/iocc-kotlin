package com.harismexis.iocck.core.qualifier

import kotlin.reflect.KClass

class TypeIdentifier(clazz: KClass<*>) : Identifier {
    override val value = clazz.simpleName!!

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Identifier -> equals(other)
            else -> false
        }
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    fun equals(identifier: Identifier): Boolean {
        return when (identifier) {
            is TypeIdentifier -> value == identifier.value
            else -> false
        }
    }

    override fun toString(): String {
        return "${javaClass.simpleName}[$value]"
    }
}