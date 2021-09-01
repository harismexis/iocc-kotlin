package com.harismexis.iocck.core.module

import com.harismexis.iocck.core.ObjectFactory
import com.harismexis.iocck.core.provider.Factory
import com.harismexis.iocck.core.provider.Singleton

inline fun <reified T> Module.singleton(noinline objectFactory: ObjectFactory<T>) =
    register(Singleton(this, objectFactory))

inline fun <reified T> Module.factory(noinline objectFactory: ObjectFactory<T>) =
    register(Factory(this, objectFactory))


