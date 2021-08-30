package com.harismexis.iocck.core.module

import com.harismexis.iocck.core.Parameters
import com.harismexis.iocck.core.qualifier.Identifier
import com.harismexis.iocck.core.qualifier.TypeIdentifier

class ModuleScope(private val module: Module) {

    fun <T> get(identifier: Identifier, parameters: Parameters = Parameters.EMPTY) =
        module.require<T>(identifier).get(parameters)

    inline fun <reified T> get(parameters: Parameters = Parameters.EMPTY) =
        get<T>(TypeIdentifier(T::class), parameters)
}