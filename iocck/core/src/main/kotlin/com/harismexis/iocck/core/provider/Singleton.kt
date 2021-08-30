package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Parameters
import com.harismexis.iocck.core.module.ModuleScope

class Singleton<T>(
    private val moduleScope: ModuleScope,
    private val factory: InstanceFactory<T>
) : Provider<T> {

    private var instance: T? = null

    override fun get(parameters: Parameters): T {
        if (instance == null) instance = factory(moduleScope, parameters)
        return instance!!
    }
}