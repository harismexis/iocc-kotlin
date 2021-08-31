package com.harismexis.iocck.core.alias

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.Container
import com.harismexis.iocck.core.module.Module

typealias ObjectFactory<T> = Module.(Args) -> T

typealias ContainerFactory = Container.Builder.() -> Unit