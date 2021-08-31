package com.harismexis.iocck.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.harismexis.iocc.android.extensions.lazyGet
import com.harismexis.iocc.android.extensions.lazyGetVm
import com.harismexis.iocck.core.Args
import com.harismexis.iocck.demo.databinding.ActivityMainBinding
import com.harismexis.iocck.demo.dependencies.AlwaysNew
import com.harismexis.iocck.demo.dependencies.Repository

class MainActivity : AppCompatActivity() {

    //    private val alwaysNew: AlwaysNew by lazyGet(Args.create("Message"))
    private val homeVm: HomeViewModel by lazyGetVm()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv1.text = homeVm.hello()
    }
}