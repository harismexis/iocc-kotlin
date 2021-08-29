package com.harismexis.iocc.core.provider

import com.harismexis.iocc.core.Parameters
import com.harismexis.iocc.core.module.ModuleScope

class Factory<T>(private val moduleScope: ModuleScope, private val factory: InstanceFactory<T>) :
    Provider<T> {
    override fun get(parameters: Parameters): T {
        return factory(moduleScope, parameters)
    }
}