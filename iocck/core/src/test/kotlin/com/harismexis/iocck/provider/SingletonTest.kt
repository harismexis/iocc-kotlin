package com.harismexis.iocck.provider

import com.harismexis.iocck.util.Recruiter
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.provider.Singleton
import org.junit.Assert
import org.junit.Test

class SingletonTest {

    @Test
    fun `Singleton should return always same instance`() {
        // given
        val module = Module()
        val provider = Singleton(module) { Recruiter(50) }

        // when
        val obj1 = provider.get()
        val obj2 = provider.get()

        // then
        Assert.assertEquals(obj1, obj2)
    }

}