package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Args

interface Provider<out T> {
    fun get(args: Args = Args.EMPTY): T
}