package com.harismexis.iocc.core.module

import com.harismexis.iocc.core.Parameters
import com.harismexis.iocc.core.qualifier.Qualifier
import com.harismexis.iocc.core.qualifier.TypeQualifier

class ModuleScope(private val module: Module) {

    fun <T> get(qualifier: Qualifier, parameters: Parameters = Parameters.EMPTY) =
        module.require<T>(qualifier).get(parameters)

    inline fun <reified T> get(parameters: Parameters = Parameters.EMPTY) =
        get<T>(TypeQualifier(T::class), parameters)
}