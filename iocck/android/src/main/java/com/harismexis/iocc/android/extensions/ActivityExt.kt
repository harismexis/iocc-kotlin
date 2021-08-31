package com.harismexis.iocc.android.extensions

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.harismexis.iocc.android.exception.DoesNotHaveContainerException
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.Container
import com.harismexis.iocck.core.container.HasContainer
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier

fun Activity.getContainer(): Container {
    val provider: HasContainer = when {
        this is HasContainer -> this
        applicationContext is HasContainer -> applicationContext as HasContainer
        else -> throw DoesNotHaveContainerException()
    }
    return provider.container
}

fun <T> Activity.get(
    identifier: Identifier,
    args: Args = Args.EMPTY
): T {
    return getContainer().get(identifier, args)
}

fun <T> Activity.lazyGet(
    identifier: Identifier,
    args: Args = Args.EMPTY
): Lazy<T> = lazy {
    get(identifier, args)
}

inline fun <reified T> Activity.get(args: Args = Args.EMPTY): T = get(
    TypeIdentifier(T::class), args
)

inline fun <reified T> Activity.lazyGet(args: Args = Args.EMPTY): Lazy<T> =
    this.lazyGet(TypeIdentifier(T::class), args)

inline fun <reified T : ViewModel> ComponentActivity.lazyGetVm(args: Args = Args.EMPTY): Lazy<T> =
    lazy { getVm(args) }

inline fun <reified T : ViewModel> ComponentActivity.getVm(args: Args = Args.EMPTY): T {
    return getContainer().getVm(this, args)
}
