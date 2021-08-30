package com.harismexis.iocck.core.module

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier

class ModuleScope(private val module: Module) {

    fun <T> get(identifier: Identifier, args: Args = Args.EMPTY) =
        module.require<T>(identifier).get(args)

    inline fun <reified T> get(args: Args = Args.EMPTY) =
        get<T>(TypeIdentifier(T::class), args)
}