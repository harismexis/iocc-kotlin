package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.module.ModuleScope

typealias InstanceFactory<T> = ModuleScope.(Args) -> T

interface Provider<out T> {
    fun get(args: Args = Args.EMPTY): T
}