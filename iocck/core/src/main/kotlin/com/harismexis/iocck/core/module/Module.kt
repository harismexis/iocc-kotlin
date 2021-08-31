package com.harismexis.iocck.core.module

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.DependencyMap
import com.harismexis.iocck.core.DependencyNotFoundException
import com.harismexis.iocck.core.alias.ObjectFactory
import com.harismexis.iocck.core.provider.Provider
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier
import com.harismexis.iocck.core.provider.Factory
import com.harismexis.iocck.core.provider.Singleton

class Module(private val dependsOn: List<Module> = emptyList()) {

    private val dependencies = DependencyMap()

    fun register(identifier: Identifier, provider: Provider<*>) {
        dependencies.put(identifier, provider)
    }

    fun <T> get(identifier: Identifier): Provider<T>? {
        return dependencies.get(identifier)
            ?: dependsOn.mapNotNull { it.get<T>(identifier) }
                .firstOrNull()
    }

    fun <T> require(identifier: Identifier): Provider<T> {
        return get(identifier) ?: throw DependencyNotFoundException(identifier)
    }

    inline fun <reified T> register(provider: Provider<T>) =
        register(TypeIdentifier(T::class), provider)

    //inline fun <reified T> get() = get<T>(TypeIdentifier(T::class))
    inline fun <reified T> require() = require<T>(TypeIdentifier(T::class))

    fun <T> get(identifier: Identifier, args: Args = Args.EMPTY) =
        require<T>(identifier).get(args)

    inline fun <reified T> get(args: Args = Args.EMPTY) =
        get<T>(TypeIdentifier(T::class), args)


    inline fun <reified T> singleton(
        identifier: Identifier,
        noinline objectFactory: ObjectFactory<T>
    ) = register(identifier, Singleton(this, objectFactory))

    inline fun <reified T> singleton(noinline objectFactory: ObjectFactory<T>) =
        register(Singleton(this, objectFactory))

    inline fun <reified T> factory(
        identifier: Identifier,
        noinline objectFactory: ObjectFactory<T>
    ) = register(identifier, Factory(this, objectFactory))

    inline fun <reified T> factory(noinline objectFactory: ObjectFactory<T>) =
        register(Factory(this, objectFactory))

}

