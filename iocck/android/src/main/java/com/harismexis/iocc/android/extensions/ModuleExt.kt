package com.harismexis.iocc.android.extensions

import androidx.lifecycle.ViewModel
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.module.ModuleScope
import com.harismexis.iocck.core.qualifier.Identifier
import com.harismexis.iocc.android.ViewModelDependencyProvider
import com.harismexis.iocc.android.ViewModelInstanceFactory

inline fun <reified T : ViewModel> Module.viewModel(identifier: Identifier, noinline instanceFactory: ViewModelInstanceFactory<T>) {
    register(identifier, ViewModelDependencyProvider(ModuleScope(this), instanceFactory))
}

inline fun <reified T : ViewModel> Module.viewModel(noinline instanceFactory: ViewModelInstanceFactory<T>) {
    register(ViewModelDependencyProvider(ModuleScope(this), instanceFactory))
}