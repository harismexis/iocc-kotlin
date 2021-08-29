package com.harismexis.iocc.core.container

import com.harismexis.iocc.core.DependencyNotFoundException
import com.harismexis.iocc.core.DuplicatedDependencyException
import com.harismexis.iocc.core.Parameters
import com.harismexis.iocc.core.module.Module
import com.harismexis.iocc.core.qualifier.Qualifier
import com.harismexis.iocc.core.qualifier.TypeQualifier

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

    fun <T> get(qualifier: Qualifier, parameters: Parameters = Parameters.EMPTY): T {
        val found = modules.mapNotNull { it.get<T>(qualifier) }
        if (found.size > 1) throw DuplicatedDependencyException(qualifier)
        val provider = found.firstOrNull() ?: throw DependencyNotFoundException(qualifier)
        return provider.get(parameters)
    }

    inline fun <reified T> get(parameters: Parameters = Parameters.EMPTY) =
        get<T>(TypeQualifier(T::class), parameters)

    fun <T> lazyInjection(qualifier: Qualifier, parameters: Parameters = Parameters.EMPTY) =
        lazy { get<T>(qualifier, parameters) }

    inline fun <reified T> lazyInjection(parameters: Parameters = Parameters.EMPTY) =
        lazy { get<T>(TypeQualifier(T::class), parameters) }
}