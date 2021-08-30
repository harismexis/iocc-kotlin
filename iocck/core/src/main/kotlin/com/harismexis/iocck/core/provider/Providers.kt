package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Parameters
import com.harismexis.iocck.core.module.ModuleScope

typealias InstanceFactory<T> = ModuleScope.(Parameters) -> T

interface Provider<out T> {
    fun get(parameters: Parameters = Parameters.EMPTY): T
}