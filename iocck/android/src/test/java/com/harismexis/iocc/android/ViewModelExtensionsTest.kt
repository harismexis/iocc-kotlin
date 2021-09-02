package com.harismexis.iocc.android

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelStore
import com.harismexis.iocc.android.extensions.lazyGetVm
import com.harismexis.iocc.android.extensions.registerVm
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import com.harismexis.iocck.core.container.Container
import com.harismexis.iocck.core.container.HasContainer
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.container.module
import org.junit.Assert

class ViewModelExtensionsTest {

    private class MockApplication(cont: Container) : Application(), HasContainer {
        override val container: Container = cont
    }

    @Test
    fun `When viewModel injected by lazy, then should work as lazy`() {
        // given
        val container = buildContainer {
            module {
                registerVm { Vm() }
            }
        }

        val mockActivity: ComponentActivity = mockk {
            every { viewModelStore } returns ViewModelStore()
            every { applicationContext } returns MockApplication(container)
        }

        // when
        val vm1: Vm by mockActivity.lazyGetVm()
        val vm2: Vm by mockActivity.lazyGetVm()

        // then
        verify(exactly = 0) { mockActivity.viewModelStore }
        Assert.assertNotNull(vm1)
        verify(exactly = 1) { mockActivity.viewModelStore }
        vm2.foo()
        verify(exactly = 2) { mockActivity.viewModelStore }
        vm2.foo()
        verify(exactly = 2) { mockActivity.viewModelStore }
    }
}