package com.harismexis.iocc.android.extensions

import androidx.lifecycle.ViewModel
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.module.ModuleScope
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocc.android.viewmodel.ViewModelDependencyProvider
import com.harismexis.iocc.android.viewmodel.ViewModelInstanceFactory

inline fun <reified T : ViewModel> Module.viewModel(
    identifier: Identifier,
    noinline factory: ViewModelInstanceFactory<T>
) {
    register(identifier, ViewModelDependencyProvider(ModuleScope(this), factory))
}

inline fun <reified T : ViewModel> Module.viewModel(
    noinline factory: ViewModelInstanceFactory<T>
) {
    register(ViewModelDependencyProvider(ModuleScope(this), factory))
}