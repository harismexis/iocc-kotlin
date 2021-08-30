package com.harismexis.iocck.demo.di

import com.harismexis.iocc.android.extensions.viewModel
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.module.factory
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.core.module.singleton
import com.harismexis.iocck.demo.ui.MainViewModel
import com.harismexis.iocck.demo.dependencies.AlwaysNew
import com.harismexis.iocck.demo.dependencies.Logger
import com.harismexis.iocck.demo.dependencies.Repository
import com.harismexis.iocck.demo.ui.HomeViewModel

object Injector {

    private val mainModule = module {
        singleton { Logger() }
        singleton { Repository() }
        factory { (value: String) -> AlwaysNew(value) }
        viewModel { (text: String) ->
            MainViewModel(text)
        }
        viewModel { (repo: Repository) ->
            HomeViewModel(repo)
        }
    }

    val container = buildContainer {
        withModule(mainModule)
    }
}