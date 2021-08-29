package com.harismexis.iocc.android

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelStore
import com.harismexis.iocc.android.extensions.lazyViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldNotBeNull
import org.junit.Test
import com.harismexis.iocc.android.extensions.viewModel
import com.harismexis.iocc.core.container.Container
import com.harismexis.iocc.core.container.HasContainer
import com.harismexis.iocc.core.extensions.component
import com.harismexis.iocc.core.extensions.module

class ViewModelExtensionsTests {

    @Test
    fun `ViewModel lazy injection should return instance when reference will be called`() {
        // arrange
        val component = component {
            module {
                viewModel { TestViewModel() }
            }
        }
        val activityMock: ComponentActivity = mockk {
            every { viewModelStore } returns ViewModelStore()
            every { applicationContext } returns ApplicationMock(component)
        }


        // act
        val testViewModel1: TestViewModel by activityMock.lazyViewModel()
        val testViewModel2: TestViewModel by activityMock.lazyViewModel()

        // assert
        verify(exactly = 0) { activityMock.viewModelStore }
        testViewModel1.shouldNotBeNull()
        testViewModel2.shouldNotBeNull()
        verify(exactly = 2) { activityMock.viewModelStore }
    }
}

private class ApplicationMock(_container: Container) : Application(), HasContainer {
    override val container: Container = _container
}