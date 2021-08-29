package com.harismexis.iocc.core.ext

import com.harismexis.iocc.core.Module

typealias ModuleFactory = Module.() -> Unit

fun module(scope: ModuleFactory) = module(emptyArray(), scope)

fun module(dependsOn: Array<Module>, factory: ModuleFactory): Module {
    val module = Module(dependsOn.toList())
    factory(module)
    return module
}