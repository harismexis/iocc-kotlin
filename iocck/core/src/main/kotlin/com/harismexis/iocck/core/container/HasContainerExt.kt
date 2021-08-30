package com.harismexis.iocck.core.container

import com.harismexis.iocck.core.Parameters
import com.harismexis.iocck.core.qualifier.Identifier
import com.harismexis.iocck.core.qualifier.TypeIdentifier

fun <T> HasContainer.get(
    identifier: Identifier,
    parameters: Parameters = Parameters.EMPTY
): T = container.get(identifier, parameters)

inline fun <reified T> HasContainer.get(parameters: Parameters = Parameters.EMPTY) =
    container.get<T>(TypeIdentifier(T::class), parameters)

inline fun <reified T> HasContainer.lazyInjection(parameters: Parameters = Parameters.EMPTY) =
    container.lazyInjection<T>(parameters)

inline fun <reified T> HasContainer.lazyInjection(
    identifier: Identifier,
    parameters: Parameters = Parameters.EMPTY
) = container.lazyInjection<T>(parameters)