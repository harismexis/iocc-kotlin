package com.harismexis.iocck.core.module

import com.harismexis.iocck.core.Dependencies
import com.harismexis.iocck.core.DependencyNotFoundException
import com.harismexis.iocck.core.provider.Provider
import com.harismexis.iocck.core.qualifier.Identifier
import com.harismexis.iocck.core.qualifier.TypeIdentifier

class Module(private val dependsOn: List<Module> = emptyList()) {

    private val dependencies = Dependencies()

    fun register(identifier: Identifier, provider: Provider<*>) {
        dependencies.put(identifier, provider)
    }

    fun <T> get(identifier: Identifier): Provider<T>? {
        return dependencies.get(identifier) ?: dependsOn.mapNotNull { it.get<T>(identifier) }.firstOrNull()
    }

    fun <T> require(identifier: Identifier): Provider<T> {
        return get(identifier) ?: throw DependencyNotFoundException(identifier)
    }

    fun scope() = ModuleScope(this)

    inline fun <reified T> register(provider: Provider<T>) = register(TypeIdentifier(T::class), provider)
    inline fun <reified T> get() = get<T>(TypeIdentifier(T::class))
    inline fun <reified T> require() = require<T>(TypeIdentifier(T::class))
}