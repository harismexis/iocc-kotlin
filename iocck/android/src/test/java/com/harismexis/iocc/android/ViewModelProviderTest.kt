package com.harismexis.iocc.android

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import com.harismexis.iocc.android.extensions.getVm
import com.harismexis.iocc.android.extensions.registerVm
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.container.module
import org.junit.Assert

class ViewModelProviderTest {

    @Test
    fun `ViewModel provider should return new instance for new activity`() {
        // given
        val vmStoreOwner: ViewModelStoreOwner = mockk {
            every { viewModelStore } answers { ViewModelStore() }
        }
        val container = buildContainer {
            module {
                registerVm { Vm() }
            }
        }

        // when
        val vm1: Vm = container.getVm(vmStoreOwner)
        val vm2: Vm = container.getVm(vmStoreOwner)

        // then
        Assert.assertNotNull(vm1)
        Assert.assertNotNull(vm2)
        Assert.assertNotEquals(vm1, vm2)
    }

    @Test
    fun `ViewModel provider should return the same instance for the same ViewModelStoreOwner`() {
        // given
        val vmStoreOwner: ViewModelStoreOwner = mockk {
            every { viewModelStore } returns ViewModelStore()
        }
        val container = buildContainer {
            module {
                registerVm { Vm() }
            }
        }

        // when
        val vm1: Vm = container.getVm(vmStoreOwner)
        val vm2: Vm = container.getVm(vmStoreOwner)

        // then
        Assert.assertNotNull(vm1)
        Assert.assertNotNull(vm2)
        Assert.assertEquals(vm1, vm2)
    }
}