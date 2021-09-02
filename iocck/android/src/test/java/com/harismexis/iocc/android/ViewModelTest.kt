package com.harismexis.iocc.android

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import com.harismexis.iocc.android.extensions.getVm
import com.harismexis.iocc.android.extensions.registerVm
import com.harismexis.iocck.core.exception.DependencyDuplicationException
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.container.module
import org.junit.Assert

class ViewModelTest {

    @Test
    fun `Should return new ViewModel for new ViewModelStore`() {
        // given
        val storeOwner = mockk<ViewModelStoreOwner> {
            every { viewModelStore } answers { ViewModelStore() }
        }
        val container = buildContainer {
            module {
                registerVm { Vm() }
            }
        }

        // when
        val vm1: Vm = container.getVm(storeOwner)
        val vm2: Vm = container.getVm(storeOwner)

        // then
        Assert.assertNotNull(vm1)
        Assert.assertNotNull(vm2)
        Assert.assertNotEquals(vm1, vm2)
    }

    @Test
    fun `Should return same ViewModel for same ViewModelStore`() {
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

    @Test(expected = DependencyDuplicationException::class)
    fun `Should throw DuplicationException when register same ViewModel`() {
        // given
        buildContainer {
            module {
                registerVm { Vm() }
                registerVm { Vm() }
            }
        }
    }

}