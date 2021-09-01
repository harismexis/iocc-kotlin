package com.harismexis.iocck.core.module

import com.harismexis.iocck.core.ModuleFactory

fun module(factory: ModuleFactory) = module(emptyArray(), factory)

fun module(dependsOn: Array<Module>, factory: ModuleFactory): Module {
    val module = Module(dependsOn.toList())
    factory(module)
    return module
}
