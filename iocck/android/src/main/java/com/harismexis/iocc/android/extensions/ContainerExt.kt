package com.harismexis.iocc.android.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.harismexis.iocc.android.viewmodel.ViewModelArgs
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.Container

inline fun <reified T : ViewModel> Container.getVm(
    viewModelStoreOwner: ViewModelStoreOwner,
    args: Args = Args.NO_ARGS
): T {
    return get(ViewModelArgs(viewModelStoreOwner, args.args.toList().toTypedArray()))
}