package com.harismexis.iocck.core.container

import com.harismexis.iocck.core.DependencyNotFoundException
import com.harismexis.iocck.core.DuplicatedDependencyException
import com.harismexis.iocck.core.Parameters
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.qualifier.Identifier
import com.harismexis.iocck.core.qualifier.TypeIdentifier

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

    fun <T> get(identifier: Identifier, parameters: Parameters = Parameters.EMPTY): T {
        val found = modules.mapNotNull { it.get<T>(identifier) }
        if (found.size > 1) throw DuplicatedDependencyException(identifier)
        val provider = found.firstOrNull() ?: throw DependencyNotFoundException(identifier)
        return provider.get(parameters)
    }

    inline fun <reified T> get(parameters: Parameters = Parameters.EMPTY) =
        get<T>(TypeIdentifier(T::class), parameters)

    fun <T> lazyInjection(identifier: Identifier, parameters: Parameters = Parameters.EMPTY) =
        lazy { get<T>(identifier, parameters) }

    inline fun <reified T> lazyInjection(parameters: Parameters = Parameters.EMPTY) =
        lazy { get<T>(TypeIdentifier(T::class), parameters) }
}