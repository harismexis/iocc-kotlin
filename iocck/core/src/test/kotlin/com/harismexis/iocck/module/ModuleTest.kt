package com.harismexis.iocck.module

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.DependencyExistsException
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.provider.Singleton
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.util.*
import org.junit.Assert
import org.junit.Test

class ModuleTest {

    @Test
    fun `Module registration should base on specified qualifier`() {
        // given
        val module = Module()
        val dev1 = Developer()
        val dev2 = Developer()

        val sing1 = Singleton(module) { dev1 }
        val sing2 = Singleton(module) { dev2 }

        // when
        module.register<Employee>(sing1)
        module.register(sing2)

        // then
        Assert.assertEquals(module.require<Employee>().get(), dev1)
        Assert.assertEquals(module.require<Developer>().get(), dev2)
    }

    @Test(expected = DependencyExistsException::class)
    fun `Should throw Exception on dependency duplication`() {
        // given
        val module = Module()

        // when
        module.register(Singleton<Employee>(module) { Developer() })
        module.register(Singleton<Employee>(module) { Developer() })
    }

    @Test
    fun `Related dependencies should be available during registration`() {
        // given
        val man = Manager()
        val dev = Developer()
        val rec = Recruiter()

        // when
        val module = module {
            singleton { man }
            singleton { dev }
            singleton { rec }

            factory {
                val man: Manager = get()
                val dev: Developer = get()
                val rec: Recruiter = get()
                Company(listOf(man, dev, rec))
            }
        }
        val company: Company = module
            .require<Company>()
            .get()

        // then
        Assert.assertTrue(company.employees.contains(man))
        Assert.assertTrue(company.employees.contains(dev))
        Assert.assertTrue(company.employees.contains(rec))
    }

    @Test
    fun `Related modules should be used to return dependency`() {
        // given
        val car = Manager()
        val bike = Developer()

        // when
        val vehiclesModule = module {
            singleton { car }
            singleton { bike }
        }

        val garageModule = module(dependsOn = arrayOf(vehiclesModule)) {
            singleton {
                val car: Manager = get()
                val bike: Developer = get()
                Company(listOf(car, bike))
            }
        }
        val company: Company = garageModule
            .require<Company>()
            .get()

        // then
        Assert.assertTrue(company.employees.contains(car))
        Assert.assertTrue(company.employees.contains(bike))
    }

    @Test
    fun `Provider parameters should be used to create dependency`() {
        // given
        val expectedSalary = 60
        val module = module {
            factory<Employee> { (salary: Int) ->
                Manager(salary)
            }
        }

        // when
        val employee = module
            .require<Employee>()
            .get(Args.create(expectedSalary))

        // then
        Assert.assertEquals(employee.salary, expectedSalary)
    }

}