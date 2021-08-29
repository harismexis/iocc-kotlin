package com.harismexis.iocck.demo.di

import com.harismexis.iocc.android.extensions.viewModel
import com.harismexis.iocc.core.extensions.component
import com.harismexis.iocc.core.extensions.factory
import com.harismexis.iocc.core.extensions.module
import com.harismexis.iocc.core.extensions.singleton
import com.harismexis.iocck.demo.ui.MainViewModel
import com.harismexis.iocck.demo.dependencies.AlwaysNew
import com.harismexis.iocck.demo.dependencies.AlwaysSame

object Injector {

    private val mainModule = module {
        singleton { AlwaysSame() }
        factory { (value: String) -> AlwaysNew(value) }
        viewModel { (text: String) ->
            MainViewModel(text)
        }
    }

    val container = component {
        withModule(mainModule)
    }
}