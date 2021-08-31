package com.harismexis.iocck.demo.application

import android.app.Application
import com.harismexis.iocck.core.container.Container
import com.harismexis.iocck.core.container.HasContainer
import com.harismexis.iocck.demo.di.AppContainer

class MainApplication : Application(), HasContainer {

    override val container: Container
        get() = AppContainer.container

}