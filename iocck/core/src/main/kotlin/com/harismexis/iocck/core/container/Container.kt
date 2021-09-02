package com.harismexis.iocck.core.container

import com.harismexis.iocck.core.exception.DependencyNotFoundException
import com.harismexis.iocck.core.exception.DependencyDuplicationException
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier

class Container(private val modules: List<Module>) {

    class Builder {
        private val modules = mutableListOf<Module>()

        fun addModule(module: Module): Builder {
            modules.add(module)
            return this
        }

        fun build() = Container(modules)
    }

    fun <T> get(identifier: Identifier, args: Args = Args.NO_ARGS): T {
        val found = modules.mapNotNull { it.get<T>(identifier) }
        if (found.size > 1) throw DependencyDuplicationException(identifier)
        val provider = found.firstOrNull() ?: throw DependencyNotFoundException(identifier)
        return provider.get(args)
    }

    inline fun <reified T> get(args: Args = Args.NO_ARGS) =
        get<T>(TypeIdentifier(T::class), args)

    fun <T> lazyGet(identifier: Identifier, args: Args = Args.NO_ARGS) =
        lazy { get<T>(identifier, args) }

    inline fun <reified T> lazyGet(args: Args = Args.NO_ARGS) =
        lazy { get<T>(TypeIdentifier(T::class), args) }
}