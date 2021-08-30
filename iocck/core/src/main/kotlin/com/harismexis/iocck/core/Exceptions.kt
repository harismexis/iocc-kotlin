package com.harismexis.iocck.core

import com.harismexis.iocck.core.identifier.Identifier
import java.lang.RuntimeException

class DependencyNotFoundException(identifier: Identifier) :
    RuntimeException("Dependency for qualifier $identifier not found")

class DependencyConflictException(identifier: Identifier) :
    RuntimeException("There is already one dependency registered for qualifier = $identifier")

class UnknownArgException : RuntimeException("Unknown Parameter")