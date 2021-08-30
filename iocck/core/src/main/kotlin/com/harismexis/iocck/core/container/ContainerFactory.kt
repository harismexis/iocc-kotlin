package com.harismexis.iocck.core.container

import com.harismexis.iocck.core.module.ModuleFactory
import com.harismexis.iocck.core.module.Module

typealias ContainerFactory = Container.Builder.() -> Unit

fun buildContainer(factory: ContainerFactory): Container {
    val builder = Container.Builder()
    factory(builder)
    return builder.build()
}

fun Container.Builder.module(moduleFactory: ModuleFactory) {
    val module = Module()
    moduleFactory(module)
    withModule(module)
}