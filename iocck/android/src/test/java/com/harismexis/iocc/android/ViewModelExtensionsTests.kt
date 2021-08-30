package com.harismexis.iocc.android

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelStore
import com.harismexis.iocc.android.extensions.lazyViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import com.harismexis.iocc.android.extensions.viewModel
import com.harismexis.iocck.core.container.Container
import com.harismexis.iocck.core.container.HasContainer
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.container.module
import org.junit.Assert

class ViewModelExtensionsTests {

    @Test
    fun `ViewModel lazy injection should return instance when reference will be called`() {
        // given
        val component = buildContainer {
            module {
                viewModel { TestViewModel() }
            }
        }
        val activityMock: ComponentActivity = mockk {
            every { viewModelStore } returns ViewModelStore()
            every { applicationContext } returns ApplicationMock(component)
        }

        // when
        val testViewModel1: TestViewModel by activityMock.lazyViewModel()
        val testViewModel2: TestViewModel by activityMock.lazyViewModel()

        // then
        verify(exactly = 0) { activityMock.viewModelStore }
        Assert.assertNotNull(testViewModel1)
        Assert.assertNotNull(testViewModel2)
        verify(exactly = 2) { activityMock.viewModelStore }
    }
}

private class ApplicationMock(_container: Container) : Application(), HasContainer {
    override val container: Container = _container
}