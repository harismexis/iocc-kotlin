package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.alias.ObjectFactory
import com.harismexis.iocck.core.module.Module

class Singleton<T>(
    private val module: Module,
    private val factory: ObjectFactory<T>
) : Provider<T> {

    private var instance: T? = null

    override fun get(args: Args): T {
        if (instance == null) instance = factory(module, args)
        return instance!!
    }
}