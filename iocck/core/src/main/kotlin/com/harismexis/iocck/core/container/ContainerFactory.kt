package com.harismexis.iocck.core.container

import com.harismexis.iocck.core.alias.ContainerFactory
import com.harismexis.iocck.core.module.ModuleFactory
import com.harismexis.iocck.core.module.Module

fun buildContainer(factory: ContainerFactory): Container {
    val builder = Container.Builder()
    factory(builder)
    return builder.build()
}

fun Container.Builder.module(moduleFactory: ModuleFactory) {
    val module = Module()
    moduleFactory(module)
    addModule(module)
}