package com.harismexis.iocck.demo.di

import com.harismexis.iocc.android.extensions.getVm
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.demo.ui.MainViewModel
import com.harismexis.iocck.demo.dependencies.AlwaysNew
import com.harismexis.iocck.demo.dependencies.Logger
import com.harismexis.iocck.demo.dependencies.Repository
import com.harismexis.iocck.demo.ui.HomeViewModel

object AppContainer {

    private val mainModule = module {
        singleton { Logger() }
        singleton { Repository() }
        factory { (value: String) -> AlwaysNew(value) }
        getVm { (text: String) ->
            MainViewModel(text)
        }
        getVm {
            val repo: Repository = this.get()
            HomeViewModel(repo)
        }
    }

    val container = buildContainer {
        addModule(mainModule)
    }
}