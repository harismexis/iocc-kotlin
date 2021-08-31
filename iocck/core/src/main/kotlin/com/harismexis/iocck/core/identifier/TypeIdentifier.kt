package com.harismexis.iocck.core.identifier

import kotlin.reflect.KClass

class TypeIdentifier(kClass: KClass<*>) : Identifier {
    override val id = kClass.simpleName!!

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Identifier -> equals(other)
            else -> false
        }
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun equals(identifier: Identifier): Boolean {
        return when (identifier) {
            is TypeIdentifier -> id == identifier.id
            else -> false
        }
    }

    override fun toString(): String {
        return "${javaClass.simpleName}[$id]"
    }
}