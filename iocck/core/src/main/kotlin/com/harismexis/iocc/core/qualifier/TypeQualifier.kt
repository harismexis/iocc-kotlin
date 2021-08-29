package com.harismexis.iocc.core.qualifier

import kotlin.reflect.KClass

class TypeQualifier(clazz: KClass<*>) : Qualifier {
    override val value = clazz.simpleName!!

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Qualifier -> equals(other)
            else -> false
        }
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    fun equals(qualifier: Qualifier): Boolean {
        return when (qualifier) {
            is TypeQualifier -> value == qualifier.value
            else -> false
        }
    }

    override fun toString(): String {
        return "${javaClass.simpleName}[$value]"
    }
}