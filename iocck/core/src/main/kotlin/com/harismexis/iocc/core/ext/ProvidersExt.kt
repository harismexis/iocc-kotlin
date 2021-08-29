package com.harismexis.iocc.core.ext

import com.harismexis.iocc.core.*

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