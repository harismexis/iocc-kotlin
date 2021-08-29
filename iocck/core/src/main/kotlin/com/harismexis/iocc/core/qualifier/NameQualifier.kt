package com.harismexis.iocc.core.qualifier

class NameQualifier(name: String) : Qualifier {
    override val value = name

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
            is NameQualifier -> value == qualifier.value
            else -> false
        }
    }

    override fun toString(): String {
        return "${javaClass.simpleName}[$value]"
    }
}