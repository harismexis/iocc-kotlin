package com.harismexis.iocc.core.extensions

import com.harismexis.iocc.core.*
import com.harismexis.iocc.core.container.Container
import com.harismexis.iocc.core.container.HasContainer
import com.harismexis.iocc.core.module.Module
import com.harismexis.iocc.core.qualifier.Qualifier
import com.harismexis.iocc.core.qualifier.TypeQualifier

typealias ComponentFactory = Container.Builder.() -> Unit

fun component(factory: ComponentFactory): Container {
    val builder = Container.Builder()
    factory(builder)
    return builder.build()
}

fun Container.Builder.module(moduleFactory: ModuleFactory) {
    val module = Module()
    moduleFactory(module)
    withModule(module)
}

fun <T> HasContainer.get(qualifier: Qualifier, parameters: Parameters = Parameters.EMPTY): T =
    container.get(qualifier, parameters)

inline fun <reified T> HasContainer.get(parameters: Parameters = Parameters.EMPTY) =
    container.get<T>(TypeQualifier(T::class), parameters)

inline fun <reified T> HasContainer.lazyInjection(parameters: Parameters = Parameters.EMPTY) =
    container.lazyInjection<T>(parameters)

inline fun <reified T> HasContainer.lazyInjection(qualifier: Qualifier, parameters: Parameters = Parameters.EMPTY) =
    container.lazyInjection<T>(parameters)