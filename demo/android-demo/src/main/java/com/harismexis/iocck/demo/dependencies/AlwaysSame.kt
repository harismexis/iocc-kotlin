package com.harismexis.iocck.demo.dependencies

class AlwaysSame {
    override fun toString(): String {
        return "AlwaysSame hash: ${hashCode()}"
    }
}
