package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Parameters
import com.harismexis.iocck.core.module.ModuleScope

class Factory<T>(
    private val moduleScope: ModuleScope,
    private val factory: InstanceFactory<T>
) : Provider<T> {

    override fun get(parameters: Parameters): T {
        return factory(moduleScope, parameters)
    }

}