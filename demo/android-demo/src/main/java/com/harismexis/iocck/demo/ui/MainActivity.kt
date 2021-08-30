package com.harismexis.iocck.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.harismexis.iocc.android.extensions.lazyInjection
import com.harismexis.iocc.android.extensions.lazyViewModel
import com.harismexis.iocck.core.Parameters
import com.harismexis.iocck.demo.R
import com.harismexis.iocck.demo.databinding.ActivityMainBinding
import com.harismexis.iocck.demo.dependencies.AlwaysNew
import com.harismexis.iocck.demo.dependencies.AlwaysSame

class MainActivity : AppCompatActivity() {

    private val singleton: AlwaysSame by lazyInjection()
    private val factory: AlwaysNew by lazyInjection(Parameters.of("Hello!"))
    private val viewModel: MainViewModel by lazyViewModel(Parameters.of("Hello from VM!"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .also {
                it.lifecycleOwner = this
                it.singleton = singleton
                it.factory = factory
                it.viewModel = viewModel
            }

    }
}