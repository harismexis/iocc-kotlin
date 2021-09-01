package com.harismexis.iocck.core.module

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.DependencyDuplicationException
import com.harismexis.iocck.core.DependencyNotFoundException
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier
import com.harismexis.iocck.core.provider.InstanceProvider
import java.util.*

class Module(private val dependsOn: List<Module> = emptyList()) {

    private val dependencies = DependencyMap()

    fun register(identifier: Identifier, provider: InstanceProvider<*>) {
        dependencies.put(identifier, provider)
    }

    inline fun <reified T> register(provider: InstanceProvider<T>) =
        register(TypeIdentifier(T::class), provider)

    fun <T> get(identifier: Identifier): InstanceProvider<T>? {
        return dependencies.get(identifier)
            ?: dependsOn.mapNotNull { it.get<T>(identifier) }
                .firstOrNull()
    }

    fun <T> require(identifier: Identifier): InstanceProvider<T> {
        return get(identifier) ?: throw DependencyNotFoundException(identifier)
    }

    fun <T> get(qualifier: Identifier, parameters: Args = Args.NO_ARGS) =
        require<T>(qualifier).get(parameters)

    inline fun <reified T> get(parameters: Args = Args.NO_ARGS) =
        get<T>(TypeIdentifier(T::class), parameters)

    private class DependencyMap {

        private val map = HashMap<Identifier, InstanceProvider<*>>()

        fun put(identifier: Identifier, provider: InstanceProvider<*>) {
            if (map.contains(identifier)) throw DependencyDuplicationException(identifier)
            map[identifier] = provider
        }

        fun <T> get(identifier: Identifier): InstanceProvider<T>? {
            return map[identifier] as? InstanceProvider<T>
        }
    }

}

