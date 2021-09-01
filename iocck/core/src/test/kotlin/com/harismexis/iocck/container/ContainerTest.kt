package com.harismexis.iocck.container

import com.harismexis.iocck.core.Args
import com.harismexis.iocck.core.container.buildContainer
import com.harismexis.iocck.core.module.factory
import com.harismexis.iocck.core.module.singleton
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
        val offer = 52

        val modSquad = module {
            factory { (salary: Int) -> Manager(salary) }
            singleton { Developer() }
        }

        val modHR = module {
            singleton { Recruiter() }
        }

        val modCompany = module(dependsOn = arrayOf(modSquad, modHR)) {
            factory {
                val man: Manager = get(Args.create(offer))
                val dev: Developer = get()
                val rec: Recruiter = get()
                Company(listOf(man, dev, rec))
            }
        }

        // when
        val container = buildContainer {
            addModule(modCompany)
        }

        val man: Manager = container.get(Args.create(expectedSalary))
        val dev: Developer = container.get()
        val rec: Recruiter = container.get()
        val company: Company = container.get()

        // then
        Assert.assertNotNull(man)
        Assert.assertEquals(man.salary, expectedSalary)
        Assert.assertNotNull(dev)
        Assert.assertNotNull(rec)

        Assert.assertTrue(company.employees[0] is Manager)
        Assert.assertEquals(company.employees.first().salary, offer)
        Assert.assertFalse(company.employees.contains(man))
        Assert.assertNotEquals(company.employees[0], man)

        Assert.assertTrue(company.employees[1] is Developer)
        Assert.assertTrue(company.employees.contains(dev))
        Assert.assertEquals(company.employees[1], dev)
        Assert.assertNotNull(dev)

        Assert.assertTrue(company.employees[2] is Recruiter)
        Assert.assertTrue(company.employees.contains(rec))
        Assert.assertEquals(company.employees[2], rec)
        Assert.assertNotNull(rec)
    }
}