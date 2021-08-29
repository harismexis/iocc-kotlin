package com.harismexis.iocc.core.module

import com.harismexis.iocc.core.*
import com.harismexis.iocc.core.provider.Provider
import com.harismexis.iocc.core.qualifier.Qualifier
import com.harismexis.iocc.core.qualifier.TypeQualifier

class Module(private val dependsOn: List<Module> = emptyList()) {

    private val dependencyRegistry = Dependencies()

    fun register(qualifier: Qualifier, provider: Provider<*>) {
        dependencyRegistry.put(qualifier, provider)
    }

    fun <T> get(qualifier: Qualifier): Provider<T>? {
        return dependencyRegistry.get(qualifier) ?: dependsOn.mapNotNull { it.get<T>(qualifier) }.firstOrNull()
    }

    fun <T> require(qualifier: Qualifier): Provider<T> {
        return get(qualifier) ?: throw DependencyNotFoundException(qualifier)
    }

    fun scope() = ModuleScope(this)

    inline fun <reified T> register(provider: Provider<T>) = register(TypeQualifier(T::class), provider)
    inline fun <reified T> get() = get<T>(TypeQualifier(T::class))
    inline fun <reified T> require() = require<T>(TypeQualifier(T::class))
}