package com.harismexis.iocc.core.provider

import com.harismexis.iocc.core.Parameters
import com.harismexis.iocc.core.module.ModuleScope

typealias InstanceFactory<T> = ModuleScope.(Parameters) -> T

interface Provider<out T> {
    fun get(parameters: Parameters = Parameters.EMPTY): T
}