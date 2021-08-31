package com.harismexis.iocck.util

interface Employee {
    val salary: Int
}

class Company(val employees: List<Employee>)

class Manager(override val salary: Int = 45) : Employee
class Developer(override val salary: Int = 40) : Employee
class Recruiter(override val salary: Int = 35) : Employee


