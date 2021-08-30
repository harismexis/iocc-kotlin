package com.harismexis.iocck.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.harismexis.iocc.android.extensions.lazyInjection
import com.harismexis.iocc.android.extensions.lazyViewModel
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.demo.R
import com.harismexis.iocck.demo.databinding.ActivityMainBinding
import com.harismexis.iocck.demo.dependencies.AlwaysNew
import com.harismexis.iocck.demo.dependencies.Repository

class MainActivity : AppCompatActivity() {

    private val repository: Repository by lazyInjection()
    private val alwaysNew: AlwaysNew by lazyInjection(Args.of("Hello!"))
    private val mainVm: MainViewModel by lazyViewModel(Args.of("Hello from VM!"))
    private val homeVm: HomeViewModel by lazyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .also {
                it.lifecycleOwner = this
                it.singleton = repository
                it.factory = alwaysNew
                it.viewModel = mainVm
            }

    }
}