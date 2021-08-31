package com.harismexis.iocck.container

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.module.module
import com.harismexis.iocck.util.Company
import com.harismexis.iocck.util.Developer
import com.harismexis.iocck.util.Manager
import com.harismexis.iocck.util.Recruiter
import org.junit.Assert
import org.junit.Test

class ContainerTest {

    @Test
    fun `Container retrieves expected dependencies`() {
        // given
        val expectedSalary = 50
        val modEngineering = module {
            factory { (salary: Int) -> Manager(salary) }
            singleton { Developer() }
        }
        val modHR = module {
            singleton { Recruiter() }
        }

        // when
        val container = buildContainer {
            addModule(modEngineering)
            addModule(modHR)
        }

        val man: Manager = container.get(Args.create(expectedSalary))
        val dev: Developer = container.get()
        val rec: Recruiter = container.get()

        // then
        Assert.assertNotNull(man)
        Assert.assertEquals(man.salary, expectedSalary)
        Assert.assertNotNull(dev)
        Assert.assertNotNull(rec)
    }

    @Test
    fun `Container retrieves correct dependency`() {
        // given
        val expectedSalary = 50
        val offer = 52
        val modEngineering = module {
            factory { (salary: Int) -> Manager(salary) }
            singleton { Developer() }
        }
        val modCompany = module(dependsOn = arrayOf(modEngineering)) {
            factory {
                val man: Manager = get(Args.create(offer))
                val dev: Developer = get()
                Company(listOf(man, dev))
            }
        }

        // when
        val container = buildContainer {
            addModule(modCompany)
        }

        val man: Manager = container.get(Args.create(expectedSalary))
        val dev: Developer = container.get()
        val company: Company = container.get()

        // then
        Assert.assertNotNull(man)
        Assert.assertEquals(man.salary, expectedSalary)
        Assert.assertNotNull(dev)
        Assert.assertEquals(company.employees.first().salary, offer)
    }
}