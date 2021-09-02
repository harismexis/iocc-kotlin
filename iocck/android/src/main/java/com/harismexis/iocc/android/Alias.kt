package com.harismexis.iocc.android

import com.harismexis.iocc.android.viewmodel.ViewModelArgs
import com.harismexis.iocck.core.module.Module

typealias ViewModelInstanceFactory<T> = Module.(ViewModelArgs) -> T