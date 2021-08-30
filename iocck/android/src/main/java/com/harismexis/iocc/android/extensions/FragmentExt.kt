package com.harismexis.iocc.android.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.HasContainer
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.identifier.TypeIdentifier

fun <T> Fragment.get(identifier: Identifier, args: Args = Args.EMPTY): T {
    return when (this) {
        is HasContainer -> container.get(identifier, args)
        else -> requireActivity().get(identifier, args)
    }
}

fun <T> Fragment.lazyInjection(
    identifier: Identifier,
    args: Args = Args.EMPTY
): Lazy<T> = lazy {
    when (this) {
        is HasContainer -> container.get(identifier, args)
        else -> requireActivity().get(identifier, args)
    }
}

inline fun <reified T> Fragment.get(args: Args = Args.EMPTY): T = get(
    TypeIdentifier(T::class), args
)

inline fun <reified T> Fragment.lazyInjection(args: Args = Args.EMPTY): Lazy<T> =
    lazyInjection(TypeIdentifier(T::class), args)

inline fun <reified T : ViewModel> Fragment.lazyViewModel(args: Args = Args.EMPTY): Lazy<T> =
    lazy { viewModel(args) }

inline fun <reified T : ViewModel> Fragment.viewModel(args: Args = Args.EMPTY): T {
    return when (this) {
        is HasContainer -> container.viewModel(this, args)
        else -> requireActivity().viewModel(args)
    }
}