package com.harismexis.iocc.android.extensions

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.harismexis.iocc.android.ViewModelParameters
import com.harismexis.iocc.core.*
import com.harismexis.iocc.core.container.Container
import com.harismexis.iocc.core.container.HasContainer
import com.harismexis.iocc.core.qualifier.Qualifier
import com.harismexis.iocc.core.qualifier.TypeQualifier

inline fun <reified T : ViewModel> Fragment.lazyViewModel(parameters: Parameters = Parameters.EMPTY): Lazy<T> = lazy { viewModel(parameters) }
inline fun <reified T : ViewModel> ComponentActivity.lazyViewModel(parameters: Parameters = Parameters.EMPTY): Lazy<T> = lazy { viewModel(parameters) }

inline fun <reified T : ViewModel> Fragment.viewModel(parameters: Parameters = Parameters.EMPTY): T {
    return when (this) {
        is HasContainer -> container.viewModel(this, parameters)
        else -> requireActivity().viewModel(parameters)
    }
}

inline fun <reified T : ViewModel> ComponentActivity.viewModel(parameters: Parameters = Parameters.EMPTY): T {
    val provider: HasContainer = when {
        this is HasContainer -> this
        applicationContext is HasContainer -> applicationContext as HasContainer
        else -> throw ComponentProviderNotDefinedException()
    }
    return provider.container.viewModel(this, parameters)
}

inline fun <reified T : ViewModel> Container.viewModel(viewModelStoreOwner: ViewModelStoreOwner, parameters: Parameters = Parameters.EMPTY): T {
    return get(ViewModelParameters(viewModelStoreOwner, parameters.parameters.toList().toTypedArray()))
}

inline fun <reified T> Fragment.get(parameters: Parameters = Parameters.EMPTY): T = get(
    TypeQualifier(T::class), parameters)
inline fun <reified T> Activity.get(parameters: Parameters = Parameters.EMPTY): T = get(
    TypeQualifier(T::class), parameters)

fun <T> Fragment.get(qualifier: Qualifier, parameters: Parameters = Parameters.EMPTY): T {
    return when (this) {
        is HasContainer -> container.get(qualifier, parameters)
        else -> requireActivity().get(qualifier, parameters)
    }
}

fun <T> Activity.get(qualifier: Qualifier, parameters: Parameters = Parameters.EMPTY): T {
    val provider: HasContainer = when {
        this is HasContainer -> this
        applicationContext is HasContainer -> applicationContext as HasContainer
        else -> throw ComponentProviderNotDefinedException()
    }
    return provider.container.get(qualifier, parameters)
}

inline fun <reified T> Fragment.lazyInjection(parameters: Parameters = Parameters.EMPTY): Lazy<T> =
    lazyInjection(TypeQualifier(T::class), parameters)

inline fun <reified T> Activity.lazyInjection(parameters: Parameters = Parameters.EMPTY): Lazy<T> =
    lazyInjection(TypeQualifier(T::class), parameters)

fun <T> Fragment.lazyInjection(
    qualifier: Qualifier,
    parameters: Parameters = Parameters.EMPTY
): Lazy<T> = lazy {
    when (this) {
        is HasContainer -> container.get(qualifier, parameters)
        else -> requireActivity().get(qualifier, parameters)
    }
}

fun <T> Activity.lazyInjection(
    qualifier: Qualifier,
    parameters: Parameters = Parameters.EMPTY
): Lazy<T> = lazy {
    val provider: HasContainer = when {
        this is HasContainer -> this
        applicationContext is HasContainer -> applicationContext as HasContainer
        else -> throw ComponentProviderNotDefinedException()
    }
    provider.container.get(qualifier, parameters)
}

class ComponentProviderNotDefinedException :
    Exception("ComponentProvider not defined! You can implement ComponentProvider in your Fragment, Activity or Application class")