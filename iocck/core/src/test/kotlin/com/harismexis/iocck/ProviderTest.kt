package com.harismexis.iocck

import com.harismexis.iocck.core.provider.Factory
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.provider.Singleton
import org.junit.Assert
import org.junit.Test

class ProvidersTests {

    @Test
    fun `Single provider should always return the same value`() {
        // given
        val module = Module()
        val provider = Singleton(module.scope()) { SampleStruct(3, "abcd") }

        // when
        val instanceA = provider.get()
        val instanceB = provider.get()

        // then
        Assert.assertEquals(instanceA, instanceB)
    }

    @Test
    fun `Factory provider should always return new value`() {
        // given
        val module = Module()
        val provider = Factory(module.scope()) { SampleStruct(3, "abcd") }

        // when
        val instanceA = provider.get()
        val instanceB = provider.get()

        // then
        Assert.assertNotEquals(instanceA, instanceB)
    }
}

private class SampleStruct(val a: Int, val b: String)