package com.harismexis.iocc.android

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import com.harismexis.iocc.android.extensions.viewModel
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.container.module
import org.junit.Assert

class ViewModelProviderTests {

    @Test
    fun `ViewModel provider should return new instance for new activity`() {
        // given
        val viewModelStoreOwner: ViewModelStoreOwner = mockk {
            every { viewModelStore } answers { ViewModelStore() }
        }
        val component = buildContainer {
            module {
                viewModel { TestViewModel() }
            }
        }

        // when
        val testVm1: TestViewModel = component.viewModel(viewModelStoreOwner)
        val testVm2: TestViewModel = component.viewModel(viewModelStoreOwner)

        // then
        Assert.assertNotNull(testVm1)
        Assert.assertNotNull(testVm2)
        Assert.assertNotEquals(testVm1, testVm2)
    }

    @Test
    fun `ViewModel provider should return the same instance for the same ViewModelStoreOwner`() {
        // given
        val viewModelStoreOwner: ViewModelStoreOwner = mockk {
            every { viewModelStore } returns ViewModelStore()
        }
        val component = buildContainer {
            module {
                viewModel { TestViewModel() }
            }
        }

        // when
        val testVm1: TestViewModel = component.viewModel(viewModelStoreOwner)
        val testVm2: TestViewModel = component.viewModel(viewModelStoreOwner)

        // then
        Assert.assertNotNull(testVm1)
        Assert.assertNotNull(testVm2)
        Assert.assertEquals(testVm1, testVm2)
    }
}