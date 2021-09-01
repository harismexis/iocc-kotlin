package com.harismexis.iocc.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.iocc.android.exception.VmArgsMissingException
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.provider.InstanceProvider

typealias ViewModelInstanceFactory<T> = Module.(ViewModelArgs) -> T

class ViewModelDependencyProvider<T : ViewModel>(
    private val module: Module,
    private val factory: ViewModelInstanceFactory<T>
) : InstanceProvider<T> {

    override fun get(args: Args): T {
        val vmArgs = args as? ViewModelArgs ?: throw VmArgsMissingException()
        val factory = ViewModelFactory(module, factory, vmArgs)
        return ViewModelProvider(vmArgs.viewModelStoreOwner, factory)
            .get(ViewModelHolder::class.java)
            .nestedViewModel as T
    }
}

private class ViewModelFactory<T : ViewModel>(
    private val module: Module,
    private val factory: ViewModelInstanceFactory<T>,
    private val args: ViewModelArgs
) : ViewModelProvider.Factory {
    override fun <R : ViewModel> create(modelClass: Class<R>): R {
        return ViewModelHolder(factory(module, args)) as R
    }
}

private class ViewModelHolder(val nestedViewModel: ViewModel) : ViewModel()