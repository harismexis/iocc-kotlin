package com.harismexis.iocck.demo

import android.app.Application
import com.harismexis.iocc.core.Component
import com.harismexis.iocc.core.ComponentProvider

class MainApplication : Application(), ComponentProvider {

    override val component: Component
        get() = DI.container

}