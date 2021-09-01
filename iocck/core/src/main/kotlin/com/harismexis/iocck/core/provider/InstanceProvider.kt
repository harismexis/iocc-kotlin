package com.harismexis.iocck.core.provider

import com.harismexis.iocck.core.Args

interface InstanceProvider<out T> {
    fun get(args: Args = Args.NO_ARGS): T
}