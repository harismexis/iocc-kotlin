package com.harismexis.iocck

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.module.factory
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.core.module.singleton
import org.junit.Assert
import org.junit.Test

class ContainerTest {

    @Test
    fun `Component should use all modules to find dependency`() {
        // given
        val expectedCarWheels = 5
        val garageCarWheels = 10
        val landVehiclesModule = module {
            factory { (wheels: Int) -> Car(wheels) }
            singleton { Bike() }
        }
        val seaVehiclesModule = module {
            singleton { Boat() }
        }
        val garageModule = module(dependsOn = arrayOf(landVehiclesModule, seaVehiclesModule)) {
            factory {
                val car: Car = get(Args.of(garageCarWheels))
                val bike: Bike = get()
                val boat: Boat = get()
                Garage(listOf(car, bike, boat))
            }
        }

        // when
        val component = buildContainer {
            withModule(garageModule)
        }
        val car: Car = component.get(Args.of(expectedCarWheels))
        val bike: Bike = component.get()
        val boat: Boat = component.get()
        val garage: Garage = component.get()

        // then
        Assert.assertNotNull(car)
        Assert.assertEquals(car.wheels, expectedCarWheels)
        Assert.assertNotNull(bike)
        Assert.assertNotNull(boat)
        Assert.assertEquals(garage.vehicles.first().wheels, garageCarWheels)
    }
}