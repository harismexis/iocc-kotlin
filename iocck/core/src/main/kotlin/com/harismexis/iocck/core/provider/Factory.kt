package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.alias.ObjectFactory
import com.harismexis.iocck.core.module.Module

class Factory<T>(
    private val module: Module,
    private val factory: ObjectFactory<T>
) : Provider<T> {

    override fun get(args: Args): T {
        return factory(module, args)
    }

}