package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.ObjectFactory
import com.harismexis.iocck.core.module.Module

class Factory<T>(
    private val module: Module,
    private val factory: ObjectFactory<T>
) : InstanceProvider<T> {

    override fun get(args: Args): T {
        return module.factory(args)
    }

}