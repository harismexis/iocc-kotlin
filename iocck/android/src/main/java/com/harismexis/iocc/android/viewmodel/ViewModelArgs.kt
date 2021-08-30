package com.harismexis.iocc.android.viewmodel

import androidx.lifecycle.ViewModelStoreOwner
import com.harismexis.iocck.core.Args

class ViewModelArgs(
    val viewModelStoreOwner: ViewModelStoreOwner,
    args: Array<Any>
) : Args(args)