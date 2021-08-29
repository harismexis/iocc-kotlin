package com.harismexis.iocc.core.provider

import com.harismexis.iocc.core.Parameters
import com.harismexis.iocc.core.module.ModuleScope

class Singleton<T>(
    private val moduleScope: ModuleScope,
    private val factory: InstanceFactory<T>
) :
    Provider<T> {

    private var instance: T? = null

    override fun get(parameters: Parameters): T {
        if (instance == null) instance = factory(moduleScope, parameters)
        return instance!!
    }
}