package com.harismexis.iocck.demo

import com.harismexis.iocc.android.ext.viewModel
import com.harismexis.iocc.core.ext.component
import com.harismexis.iocc.core.ext.singleton
import com.harismexis.iocc.core.ext.factory
import com.harismexis.iocc.core.ext.module

class SimpleClass {
    override fun toString(): String {
        return "SimpleClass: ${hashCode()}"
    }
}

class ComplexClass(val value: String) {
    override fun toString(): String {
        return "ComplexClass: ${hashCode()}"
    }
}

object DI {

    private val mainModule = module {
        singleton { SimpleClass() }
        factory { (value: String) -> ComplexClass(value) }
        viewModel { (text: String) ->
            MainViewModel(text)
        }
    }

    val container = component {
        withModule(mainModule)
    }
}