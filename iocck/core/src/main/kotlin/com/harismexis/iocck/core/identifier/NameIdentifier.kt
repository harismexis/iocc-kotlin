package com.harismexis.iocck.core.identifier

class NameIdentifier(name: String) : Identifier {
    override val value = name

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
            is NameIdentifier -> value == identifier.value
            else -> false
        }
    }

    override fun toString(): String {
        return "${javaClass.simpleName}[$value]"
    }
}