package com.harismexis.iocck.core.module

import com.harismexis.iocck.core.provider.Factory
import com.harismexis.iocck.core.provider.InstanceFactory
import com.harismexis.iocck.core.provider.Singleton
import com.harismexis.iocck.core.qualifier.Identifier

inline fun <reified T> Module.singleton(identifier: Identifier, noinline instanceFactory: InstanceFactory<T>) {
    register(identifier, Singleton(ModuleScope(this), instanceFactory))
}

inline fun <reified T> Module.singleton(noinline instanceFactory: InstanceFactory<T>) {
    register(Singleton(ModuleScope(this), instanceFactory))
}

inline fun <reified T> Module.factory(identifier: Identifier, noinline instanceFactory: InstanceFactory<T>) {
    register(identifier, Factory(ModuleScope(this), instanceFactory))
}

inline fun <reified T> Module.factory(noinline instanceFactory: InstanceFactory<T>) {
    register(Factory(ModuleScope(this), instanceFactory))
}