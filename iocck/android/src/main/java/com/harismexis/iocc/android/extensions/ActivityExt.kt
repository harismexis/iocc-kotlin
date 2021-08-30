package com.harismexis.iocc.android.extensions

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.harismexis.iocc.android.exception.HasContainerNotImplementedException
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.HasContainer
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier

fun <T> Activity.get(
    identifier: Identifier,
    args: Args = Args.EMPTY
): T {
    val provider: HasContainer = when {
        this is HasContainer -> this
        applicationContext is HasContainer -> applicationContext as HasContainer
        else -> throw HasContainerNotImplementedException()
    }
    return provider.container.get(identifier, args)
}

fun <T> Activity.lazyInjection(
    identifier: Identifier,
    args: Args = Args.EMPTY
): Lazy<T> = lazy {
    val provider: HasContainer = when {
        this is HasContainer -> this
        applicationContext is HasContainer -> applicationContext as HasContainer
        else -> throw HasContainerNotImplementedException()
    }
    provider.container.get(identifier, args)
}

inline fun <reified T> Activity.get(args: Args = Args.EMPTY): T = get(
    TypeIdentifier(T::class), args
)

inline fun <reified T> Activity.lazyInjection(args: Args = Args.EMPTY): Lazy<T> =
    lazyInjection(TypeIdentifier(T::class), args)

inline fun <reified T : ViewModel> ComponentActivity.lazyViewModel(args: Args = Args.EMPTY): Lazy<T> =
    lazy { viewModel(args) }

inline fun <reified T : ViewModel> ComponentActivity.viewModel(args: Args = Args.EMPTY): T {
    val provider: HasContainer = when {
        this is HasContainer -> this
        applicationContext is HasContainer -> applicationContext as HasContainer
        else -> throw HasContainerNotImplementedException()
    }
    return provider.container.viewModel(this, args)
}
