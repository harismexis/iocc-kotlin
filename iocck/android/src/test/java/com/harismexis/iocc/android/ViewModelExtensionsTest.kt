package com.harismexis.iocc.android

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelStore
import com.harismexis.iocc.android.extensions.lazyGetVm
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import com.harismexis.iocc.android.extensions.registerVm
import com.harismexis.iocck.core.container.Container
import com.harismexis.iocck.core.container.HasContainer
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.container.module
import org.junit.Assert

class ViewModelExtensionsTest {

    @Test
    fun `ViewModel lazy injection should return instance when reference will be called`() {
        // given
        val container = buildContainer {
            module {
                registerVm { Vm() }
            }
        }

        val activityMock: ComponentActivity = mockk {
            every { viewModelStore } returns ViewModelStore()
            every { applicationContext } returns ApplicationMock(container)
        }

        // when
        val vm1: Vm by activityMock.lazyGetVm()
        val vm2: Vm by activityMock.lazyGetVm()

        // then
        verify(exactly = 0) { activityMock.viewModelStore }
        Assert.assertNotNull(vm1)
        Assert.assertNotNull(vm2)
        verify(exactly = 2) { activityMock.viewModelStore }
    }
}

private class ApplicationMock(_container: Container) : Application(), HasContainer {
    override val container: Container = _container
}