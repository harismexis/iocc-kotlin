package com.harismexis.iocck.core.container

import com.harismexis.iocck.core.DependencyNotFoundException
import com.harismexis.iocck.core.DependencyConflictException
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier

class Container(private val modules: List<Module>) {

    class Builder {
        private val modules = mutableListOf<Module>()

        fun withModule(module: Module): Builder {
            modules.add(module)
            return this
        }

        fun withModules(modules: Array<Module>): Builder {
            this.modules.addAll(modules)
            return this
        }

        fun build() = Container(modules)
    }

    fun <T> get(identifier: Identifier, args: Args = Args.EMPTY): T {
        val found = modules.mapNotNull { it.get<T>(identifier) }
        if (found.size > 1) throw DependencyConflictException(identifier)
        val provider = found.firstOrNull() ?: throw DependencyNotFoundException(identifier)
        return provider.get(args)
    }

    inline fun <reified T> get(args: Args = Args.EMPTY) =
        get<T>(TypeIdentifier(T::class), args)

    fun <T> lazyInjection(identifier: Identifier, args: Args = Args.EMPTY) =
        lazy { get<T>(identifier, args) }

    inline fun <reified T> lazyInjection(args: Args = Args.EMPTY) =
        lazy { get<T>(TypeIdentifier(T::class), args) }
}