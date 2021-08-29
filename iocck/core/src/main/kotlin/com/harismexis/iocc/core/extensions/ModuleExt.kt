package com.harismexis.iocc.core.extensions

import com.harismexis.iocc.core.module.Module
import com.harismexis.iocc.core.module.ModuleScope
import com.harismexis.iocc.core.provider.Factory
import com.harismexis.iocc.core.provider.InstanceFactory
import com.harismexis.iocc.core.provider.Singleton
import com.harismexis.iocc.core.qualifier.Qualifier

typealias ModuleFactory = Module.() -> Unit

fun module(scope: ModuleFactory) = module(emptyArray(), scope)

fun module(dependsOn: Array<Module>, factory: ModuleFactory): Module {
    val module = Module(dependsOn.toList())
    factory(module)
    return module
}

inline fun <reified T> Module.singleton(qualifier: Qualifier, noinline instanceFactory: InstanceFactory<T>) {
    register(qualifier, Singleton(ModuleScope(this), instanceFactory))
}

inline fun <reified T> Module.singleton(noinline instanceFactory: InstanceFactory<T>) {
    register(Singleton(ModuleScope(this), instanceFactory))
}

inline fun <reified T> Module.factory(qualifier: Qualifier, noinline instanceFactory: InstanceFactory<T>) {
    register(qualifier, Factory(ModuleScope(this), instanceFactory))
}

inline fun <reified T> Module.factory(noinline instanceFactory: InstanceFactory<T>) {
    register(Factory(ModuleScope(this), instanceFactory))
}