package com.harismexis.iocc.android.ext

import androidx.lifecycle.ViewModel
import com.harismexis.iocc.core.Module
import com.harismexis.iocc.core.ModuleScope
import com.harismexis.iocc.core.Qualifier
import com.harismexis.iocc.android.ViewModelDependencyProvider
import com.harismexis.iocc.android.ViewModelInstanceFactory

inline fun <reified T : ViewModel> Module.viewModel(qualifier: Qualifier, noinline instanceFactory: ViewModelInstanceFactory<T>) {
    register(qualifier, ViewModelDependencyProvider(ModuleScope(this), instanceFactory))
}

inline fun <reified T : ViewModel> Module.viewModel(noinline instanceFactory: ViewModelInstanceFactory<T>) {
    register(ViewModelDependencyProvider(ModuleScope(this), instanceFactory))
}