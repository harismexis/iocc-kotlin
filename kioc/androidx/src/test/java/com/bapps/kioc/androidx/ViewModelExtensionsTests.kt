package com.bapps.kioc.androidx

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelStore
import com.harismexis.iocc.android.ext.lazyViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldNotBeNull
import org.junit.Test
import com.harismexis.iocc.android.ext.viewModel
import com.harismexis.iocc.core.Component
import com.harismexis.iocc.core.ComponentProvider
import com.harismexis.iocc.core.ext.component
import com.harismexis.iocc.core.ext.module

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

private class ApplicationMock(_component: Component) : Application(), ComponentProvider {
    override val component: Component = _component
}