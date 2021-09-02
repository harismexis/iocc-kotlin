package com.harismexis.iocck.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.harismexis.iocc.android.extensions.lazyGet
import com.harismexis.iocc.android.extensions.lazyGetVm
import com.harismexis.iocck.demo.databinding.ActivityMainBinding
import com.harismexis.iocck.demo.dependencies.Logger

class MainActivity : AppCompatActivity() {

    private val logger: Logger by lazyGet()
    private val homeVm: HomeViewModel by lazyGetVm()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv1.text = homeVm.msg()
        binding.tv2.text = homeVm.hello()
    }
}