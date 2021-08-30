package com.harismexis.iocck

import com.harismexis.iocck.core.DependencyConflictException
import com.harismexis.iocck.core.module.ModuleScope
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.identifier.Identifier
import com.harismexis.iocck.core.provider.Singleton
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.core.module.singleton
import com.harismexis.iocck.core.identifier.NameIdentifier
import org.junit.Assert
import org.junit.Test

class ModuleTest {

    @Test
    fun `Module registration should base on specified qualifier`() {
        // given
        val module = Module()
        val firstCar = Car()
        val secondCar = Car()

        val firstCarProvider = Singleton(module.scope()) { firstCar }
        val secondCarProvider = Singleton(module.scope()) { secondCar }

        // when
        module.register<Vehicle>(firstCarProvider)
        module.register(secondCarProvider)

        // then
        Assert.assertEquals(module.require<Vehicle>().get(), firstCar)
        Assert.assertEquals(module.require<Car>().get(), secondCar)
    }

    @Test(expected = DependencyConflictException::class)
    fun `Module should throw exception on second registration of the same type`() {
        // given
        val module = Module()

        // when
        module.register(Singleton<Vehicle>(ModuleScope(module)) { Car() })
        module.register(Singleton<Vehicle>(ModuleScope(module)) { Car() })
    }

    @Test
    fun `Name qualifiers should allow to register the same type twice`() {
        // given
        val module = Module()
        val expectedCar = Car()
        val expectedBike = Bike()
        val identifierFirst: Identifier = NameIdentifier("first")
        val identifierSecond: Identifier = NameIdentifier("second")

        // when
        module.register(identifierFirst, Singleton(module.scope()) { expectedCar })
        module.register(identifierSecond, Singleton(module.scope()) { expectedBike })
        val carProvider = module.require<Vehicle>(identifierFirst)
        val bikeProvider = module.require<Vehicle>(identifierSecond)

        // then
        Assert.assertEquals(carProvider.get(), expectedCar)
        Assert.assertEquals(bikeProvider.get(), expectedBike)
    }

    @Test
    fun `There should be relation between not dependent modules`() {
        // given
        val carModule = module {
            singleton<Vehicle> { Car() }
        }
        val boatModule = module {
            singleton<Vehicle> { Boat() }
        }

        // when
        val carProvider = carModule.require<Vehicle>()
        val boatProvider = boatModule.require<Vehicle>()

        // then
        Assert.assertTrue(carProvider.get() is Car)
        Assert.assertTrue(boatProvider.get() is Boat)
    }
}