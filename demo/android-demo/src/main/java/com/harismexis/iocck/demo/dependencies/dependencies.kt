package com.harismexis.iocck.demo.dependencies

class AlwaysNew(val value: String) {
    override fun toString(): String {
        return "AlwaysNew hash: ${hashCode()}"
    }
}

class Bar(val title: Title) {
    override fun toString(): String {
        return "AlwaysNew hash: ${hashCode()}"
    }
}

class Title {
    override fun toString(): String {
        return "AlwaysNew hash: ${hashCode()}"
    }
}

interface Loop {
    fun hello(): String
}

class Loop1 constructor(val loop: Loop?) : Loop {
    override fun hello(): String {
        return "Hello from Loop1"
    }
}

class Loop2 constructor(val loop: Loop?) : Loop {
    override fun hello(): String {
        return "Hello from Loop2"
    }
}