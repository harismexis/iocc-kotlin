package com.harismexis.iocc.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.iocc.android.ViewModelInstanceFactory
import com.harismexis.iocc.android.exception.VmArgsMissingException
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.provider.InstanceProvider

class ViewModelInstanceProvider<T : ViewModel>(
    private val module: Module,
    private val factory: ViewModelInstanceFactory<T>
) : InstanceProvider<T> {

    override fun get(args: Args): T {
        val vmArgs = args as? ViewModelArgs ?: throw VmArgsMissingException()
        val factory = ViewModelFactory(module, factory, vmArgs)
        return ViewModelProvider(vmArgs.storeOwner, factory)
            .get(ViewModelWrapper::class.java)
            .viewModel as T
    }
}

