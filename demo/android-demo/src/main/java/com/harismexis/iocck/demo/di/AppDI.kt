package com.harismexis.iocck.demo.di

import com.harismexis.iocc.android.extensions.registerVm
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.module.factory
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.core.module.singleton
import com.harismexis.iocck.demo.dependencies.*
import com.harismexis.iocck.demo.ui.HomeViewModel

object AppDI {

    private val mainModule = module {
        // Register Singleton
        singleton { Repository() }
        // register( Singleton(this) { Repository() })
        // register(TypeIdentifier(Repository::class), Singleton(this) { Repository() })

        // Register Factory
        factory { (value: String) -> AlwaysNew(value) }

        // Register ViewModel
        registerVm {
            val repo: Repository = get()
            HomeViewModel(repo, "default message")
        }
    }

    val container = buildContainer {
        addModule(mainModule)
    }
}