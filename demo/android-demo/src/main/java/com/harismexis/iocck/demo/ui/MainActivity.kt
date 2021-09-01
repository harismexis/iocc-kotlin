package com.harismexis.iocck.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.harismexis.iocc.android.extensions.lazyGetVm
import com.harismexis.iocck.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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