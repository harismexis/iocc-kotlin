package com.harismexis.iocc.android.extensions

import androidx.lifecycle.ViewModel
import com.harismexis.iocc.android.ViewModelInstanceFactory
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocc.android.viewmodel.ViewModelInstanceProvider

inline fun <reified T : ViewModel> Module.registerVm(
    noinline factory: ViewModelInstanceFactory<T>
) {
    register(ViewModelInstanceProvider(this, factory))
}