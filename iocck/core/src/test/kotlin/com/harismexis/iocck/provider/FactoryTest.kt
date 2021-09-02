package com.harismexis.iocck.provider

import com.harismexis.iocck.util.Manager
import com.harismexis.iocck.core.provider.Factory
import com.harismexis.iocck.core.module.Module
import org.junit.Assert
import org.junit.Test

class FactoryTest {

    @Test
    fun `Factory should return always new object`() {
        // given
        val module = Module()
        val provider = Factory(module) { Manager(50) }

        // when
        val obj1 = provider.get()
        val obj2 = provider.get()

        // then
        Assert.assertNotEquals(obj1, obj2)
    }

}