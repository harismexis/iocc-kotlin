package com.bapps.kioc.sampleapp

import android.app.Application
import com.harismexis.iocc.core.Component
import com.harismexis.iocc.core.ComponentProvider

class AndroidApplication : Application(), ComponentProvider {

    override val component: Component
        get() = DI.container

}