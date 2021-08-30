package com.harismexis.iocc.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.iocc.android.exception.ViewModelArgsExpectedException
import com.harismexis.iocck.core.module.ModuleScope
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.provider.Provider

typealias ViewModelInstanceFactory<T> = ModuleScope.(ViewModelArgs) -> T

class ViewModelDependencyProvider<T : ViewModel>(
    private val scope: ModuleScope,
    private val factory: ViewModelInstanceFactory<T>
) : Provider<T> {

    override fun get(args: Args): T {
        val vmArgs = args as? ViewModelArgs ?: throw ViewModelArgsExpectedException()
        val factory = ViewModelFactory(scope, factory, vmArgs)
        return ViewModelProvider(vmArgs.viewModelStoreOwner, factory)
            .get(ViewModelHolder::class.java)
            .nestedViewModel as T
    }
}

private class ViewModelFactory<T : ViewModel>(
    private val scope: ModuleScope,
    private val factory: ViewModelInstanceFactory<T>,
    private val args: ViewModelArgs
) : ViewModelProvider.Factory {
    override fun <R : ViewModel> create(modelClass: Class<R>): R {
        return ViewModelHolder(factory(scope, args)) as R
    }
}

private class ViewModelHolder(val nestedViewModel: ViewModel) : ViewModel()