package com.harismexis.iocck.core.container

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier

fun <T> HasContainer.get(
    identifier: Identifier,
    args: Args = Args.EMPTY
): T = container.get(identifier, args)

inline fun <reified T> HasContainer.get(args: Args = Args.EMPTY) =
    container.get<T>(TypeIdentifier(T::class), args)

inline fun <reified T> HasContainer.lazyGet(args: Args = Args.EMPTY) =
    container.lazyGet<T>(args)

inline fun <reified T> HasContainer.lazyGet(
    identifier: Identifier,
    args: Args = Args.EMPTY
) = container.lazyGet<T>(args)