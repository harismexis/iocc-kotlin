package com.harismexis.iocck.demo.ui

import androidx.lifecycle.ViewModel

class MainViewModel(val text: String) : ViewModel() {

    override fun toString(): String {
        return "MainViewModel: ${hashCode()}"
    }
}