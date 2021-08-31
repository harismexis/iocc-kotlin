package com.harismexis.iocck.core

import com.harismexis.iocck.core.identifier.Identifier
import java.lang.RuntimeException

class DependencyNotFoundException(identifier: Identifier) :
    RuntimeException("Dependency for $identifier not found")

class DependencyExistsException(identifier: Identifier) :
    RuntimeException("Dependency already exists for $identifier")

class UnknownArgException : RuntimeException("Unknown argument")