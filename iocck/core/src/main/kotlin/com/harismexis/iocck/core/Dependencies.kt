package com.harismexis.iocck.core

import com.harismexis.iocck.core.provider.Provider
import com.harismexis.iocck.core.qualifier.Identifier

class Dependencies {
    private val dependencies = HashMap<Identifier, Provider<*>>()

    fun put(identifier: Identifier, provider: Provider<*>) {
        if (dependencies.contains(identifier))
            throw DuplicatedDependencyException(identifier)

        dependencies[identifier] = provider
    }

    fun <T> get(identifier: Identifier): Provider<T>? {
        return dependencies[identifier] as? Provider<T>
    }
}

