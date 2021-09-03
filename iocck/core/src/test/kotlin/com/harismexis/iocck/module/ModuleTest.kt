package com.harismexis.iocck.module

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.exception.DependencyDuplicationException
import com.harismexis.iocck.core.module.factory
import com.harismexis.iocck.core.module.singleton
import com.harismexis.iocck.core.module.Module
import com.harismexis.iocck.core.provider.Singleton
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.core.provider.Factory
import com.harismexis.iocck.util.*
import org.junit.Assert
import org.junit.Test

class ModuleTest {

    @Test
    fun `Expected arguments are retrieved from dependency`() {
        // given
        val expectedSalary = 60
        val module = module {
            factory<Employee> { (salary: Int) ->
                Manager(salary)
            }
        }

        // when
        val emp: Employee = module.get(Args.create(expectedSalary))

        // then
        Assert.assertEquals(emp.salary, expectedSalary)
    }

    @Test
    fun `Module retrieves dependency according to type`() {
        // given
        val module = Module()
        val dev1 = Developer()
        val dev2 = Developer()

        // when
        module.register<Employee>(Singleton(module) { dev1 })
        module.register(Singleton(module) { dev2 })

        // then
        Assert.assertEquals(module.get<Employee>(), dev1)
        Assert.assertEquals(module.get<Developer>(), dev2)
    }

    @Test
    fun `Dependencies are found in same module`() {
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
                val manager: Manager = get()
                val developer: Developer = get()
                val recruiter: Recruiter = get()
                Company(listOf(manager, developer, recruiter))
            }
        }
        val company: Company = module.get()

        // then
        Assert.assertTrue(company.employees.contains(man))
        Assert.assertTrue(company.employees.contains(dev))
        Assert.assertTrue(company.employees.contains(rec))
    }

    @Test
    fun `Dependencies are found in dependsOn modules`() {
        // given
        val man = Manager()
        val dev = Developer()

        // when
        val modSquad = module {
            singleton { man }
            singleton { dev }
        }

        val modCompany = module(dependsOn = arrayOf(modSquad)) {
            singleton {
                val manager: Manager = get()
                val developer: Developer = get()
                Company(listOf(manager, developer))
            }
        }
        val company: Company = modCompany.get()

        // then
        Assert.assertTrue(company.employees.contains(man))
        Assert.assertTrue(company.employees.contains(dev))
    }

    @Test(expected = DependencyDuplicationException::class)
    fun `Throws Duplication Exception when register same Singleton`() {
        // given
        val module = Module()

        // when
        module.register(Singleton<Employee>(module) { Developer() })
        module.register(Singleton<Employee>(module) { Developer() })
    }

    @Test(expected = DependencyDuplicationException::class)
    fun `Throws Duplication Exception when register same class with Factory & Singleton`() {
        // given
        val module = Module()

        // when
        module.register(Singleton<Employee>(module) { Manager() })
        module.register(Factory<Employee>(module) { Manager() })
    }

    @Test(expected = DependencyDuplicationException::class)
    fun `Throws Duplication Exception when register same Factory`() {
        // given
        val module = Module()

        // when
        module.register(Factory<Employee>(module) { Recruiter() })
        module.register(Factory<Employee>(module) { Recruiter() })
    }

}