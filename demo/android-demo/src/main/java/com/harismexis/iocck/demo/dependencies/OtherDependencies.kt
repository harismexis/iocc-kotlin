package com.harismexis.iocck.demo.dependencies

class AlwaysNew(val value: String) {

    override fun toString(): String {
        return "AlwaysNew hash code: ${hashCode()}"
    }

}