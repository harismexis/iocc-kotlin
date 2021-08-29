package com.harismexis.iocck.demo

import androidx.lifecycle.ViewModel

class MainViewModel(val text: String) : ViewModel() {

    override fun toString(): String {
        return "MainViewModel: ${hashCode()}"
    }
}