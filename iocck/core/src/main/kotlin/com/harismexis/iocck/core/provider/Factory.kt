package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.module.ModuleScope

class Factory<T>(
    private val moduleScope: ModuleScope,
    private val factory: InstanceFactory<T>
) : Provider<T> {

    override fun get(args: Args): T {
        return factory(moduleScope, args)
    }

}