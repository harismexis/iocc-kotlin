package com.harismexis.iocck.core.exception

import com.harismexis.iocck.core.identifier.Identifier
import java.lang.RuntimeException

class DependencyNotFoundException(identifier: Identifier) :
    RuntimeException("Dependency for $identifier not found")

class DependencyDuplicationException(identifier: Identifier) :
    RuntimeException("Dependency duplication for $identifier")

class UnknownArgException : RuntimeException("Unknown argument")