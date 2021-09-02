package com.harismexis.iocc.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.iocc.android.ViewModelInstanceFactory
import com.harismexis.iocck.core.module.Module

internal class ViewModelFactory<T : ViewModel>(
    private val module: Module,
    private val factory: ViewModelInstanceFactory<T>,
    private val args: ViewModelArgs
) : ViewModelProvider.Factory {
    override fun <R : ViewModel> create(modelClass: Class<R>): R {
        return ViewModelWrapper(module.factory(args)) as R
    }
}

internal class ViewModelWrapper(val viewModel: ViewModel) : ViewModel()