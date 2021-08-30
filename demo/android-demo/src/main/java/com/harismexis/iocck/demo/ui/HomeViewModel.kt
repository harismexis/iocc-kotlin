package com.harismexis.iocck.demo.ui

import androidx.lifecycle.ViewModel
import com.harismexis.iocck.demo.dependencies.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun callRepository(): String {
        return repository.toString()
    }

    override fun toString(): String {
        return "HomeViewModel: ${hashCode()}"
    }
}