package com.harismexis.iocck.demo.application

import android.app.Application
import com.harismexis.iocc.core.container.Container
import com.harismexis.iocc.core.container.HasContainer
import com.harismexis.iocck.demo.di.Injector

class MainApplication : Application(), HasContainer {

    override val container: Container
        get() = Injector.container

}