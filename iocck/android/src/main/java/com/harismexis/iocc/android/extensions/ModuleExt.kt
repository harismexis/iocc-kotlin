package com.harismexis.iocc.android.extensions

import androidx.lifecycle.ViewModel
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocc.android.viewmodel.ViewModelDependencyProvider
import com.harismexis.iocc.android.viewmodel.ViewModelInstanceFactory

inline fun <reified T : ViewModel> Module.getVm(
    identifier: Identifier,
    noinline factory: ViewModelInstanceFactory<T>
) {
    register(identifier, ViewModelDependencyProvider(this, factory))
}

inline fun <reified T : ViewModel> Module.registerVm(
    noinline factory: ViewModelInstanceFactory<T>
) {
    register(ViewModelDependencyProvider(this, factory))
}