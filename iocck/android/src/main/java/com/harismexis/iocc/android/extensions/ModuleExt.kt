package com.harismexis.iocc.android.extensions

import androidx.lifecycle.ViewModel
import com.harismexis.iocc.core.module.Module
import com.harismexis.iocc.core.module.ModuleScope
import com.harismexis.iocc.core.qualifier.Qualifier
import com.harismexis.iocc.android.ViewModelDependencyProvider
import com.harismexis.iocc.android.ViewModelInstanceFactory

inline fun <reified T : ViewModel> Module.viewModel(qualifier: Qualifier, noinline instanceFactory: ViewModelInstanceFactory<T>) {
    register(qualifier, ViewModelDependencyProvider(ModuleScope(this), instanceFactory))
}

inline fun <reified T : ViewModel> Module.viewModel(noinline instanceFactory: ViewModelInstanceFactory<T>) {
    register(ViewModelDependencyProvider(ModuleScope(this), instanceFactory))
}