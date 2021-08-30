package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.module.ModuleScope

class Singleton<T>(
    private val moduleScope: ModuleScope,
    private val factory: InstanceFactory<T>
) : Provider<T> {

    private var instance: T? = null

    override fun get(args: Args): T {
        if (instance == null) instance = factory(moduleScope, args)
        return instance!!
    }
}