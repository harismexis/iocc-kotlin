package com.harismexis.iocc.android.exception

class InvalidVmArgsException :
    RuntimeException("Args is not ViewModelArgs")

class DoesNotHaveContainerException :
    Exception("Does not implement HasContainer")