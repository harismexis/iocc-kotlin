package com.harismexis.iocck.core

import com.harismexis.iocck.core.provider.Provider
import com.harismexis.iocck.core.identifier.Identifier

class DependencyMap {
    private val map = HashMap<Identifier, Provider<*>>()

    fun put(identifier: Identifier, provider: Provider<*>) {
        if (map.contains(identifier)) throw DependencyExistsException(identifier)
        map[identifier] = provider
    }

    fun <T> get(identifier: Identifier): Provider<T>? {
        return map[identifier] as? Provider<T>
    }
}

