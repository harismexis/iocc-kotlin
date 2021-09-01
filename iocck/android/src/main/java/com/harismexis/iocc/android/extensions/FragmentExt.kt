package com.harismexis.iocc.android.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.HasContainer
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier

fun <T> Fragment.get(identifier: Identifier, args: Args = Args.NO_ARGS): T {
    return when (this) {
        is HasContainer -> container.get(identifier, args)
        else -> requireActivity().get(identifier, args)
    }
}

fun <T> Fragment.lazyGet(
    identifier: Identifier,
    args: Args = Args.NO_ARGS
): Lazy<T> = lazy {
    get(identifier, args)
}

inline fun <reified T> Fragment.get(args: Args = Args.NO_ARGS): T =
    get(TypeIdentifier(T::class), args)

inline fun <reified T> Fragment.lazyGet(args: Args = Args.NO_ARGS): Lazy<T> =
    lazyGet(TypeIdentifier(T::class), args)

inline fun <reified T : ViewModel> Fragment.lazyGetVm(args: Args = Args.NO_ARGS): Lazy<T> =
    lazy { getVm(args) }

inline fun <reified T : ViewModel> Fragment.getVm(args: Args = Args.NO_ARGS): T {
    return when (this) {
        is HasContainer -> container.getVm(this, args)
        else -> requireActivity().getVm(args)
    }
}