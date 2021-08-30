package com.harismexis.iocck.core.module

typealias ModuleFactory = Module.() -> Unit

fun module(scope: ModuleFactory) = module(emptyArray(), scope)

fun module(dependsOn: Array<Module>, factory: ModuleFactory): Module {
    val module = Module(dependsOn.toList())
    factory(module)
    return module
}
