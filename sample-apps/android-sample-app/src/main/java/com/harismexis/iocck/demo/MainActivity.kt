package com.harismexis.iocck.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.harismexis.iocc.android.ext.lazyInjection
import com.harismexis.iocc.android.ext.lazyViewModel
import com.harismexis.iocc.core.Parameters
import com.harismexis.iocck.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val singleton: SimpleClass by lazyInjection()
    private val factory: ComplexClass by lazyInjection(Parameters.of("Hello!"))
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