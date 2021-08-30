package com.harismexis.iocck

import com.harismexis.iocck.core.Parameters
import com.harismexis.iocck.core.module.factory
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.core.module.singleton
import org.junit.Assert
import org.junit.Test

class ModuleExtTest {

    @Test
    fun `Related dependencies should be available during registration`() {
        // given
        val car = Car()
        val bike = Bike()
        val boat = Boat()

        // when
        val module = module {
            singleton { car }
            singleton { bike }
            singleton { boat }

            factory {
                val car: Car = get()
                val bike: Bike = get()
                val boat: Boat = get()
                Garage(listOf(car, bike, boat))
            }
        }
        val garage: Garage = module
            .require<Garage>()
            .get()

        // then
        Assert.assertTrue(garage.vehicles.contains(car))
        Assert.assertTrue(garage.vehicles.contains(bike))
        Assert.assertTrue(garage.vehicles.contains(boat))
    }

    @Test
    fun `Related modules should be used to return dependency`() {
        // given
        val car = Car()
        val bike = Bike()

        // when
        val vehiclesModule = module {
            singleton { car }
            singleton { bike }
        }

        val garageModule = module(dependsOn = arrayOf(vehiclesModule)) {
            singleton {
                val car: Car = get()
                val bike: Bike = get()
                Garage(listOf(car, bike))
            }
        }
        val garage: Garage = garageModule
            .require<Garage>()
            .get()

        // then
        Assert.assertTrue(garage.vehicles.contains(car))
        Assert.assertTrue(garage.vehicles.contains(bike))
    }

    @Test
    fun `Provider parameters should be used to create dependency`() {
        // given
        val expectedWheelsValue = 10
        val module = module {
            factory<Vehicle> { (wheels: Int) ->
                Car(wheels)
            }
        }

        // when
        val vehicle = module
            .require<Vehicle>()
            .get(Parameters.of(expectedWheelsValue))

        // then
        Assert.assertEquals(vehicle.wheels, expectedWheelsValue)
    }
}