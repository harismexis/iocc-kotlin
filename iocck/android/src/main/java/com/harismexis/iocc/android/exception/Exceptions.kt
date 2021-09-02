package com.harismexis.iocc.android.exception

class VmArgsMissingException :
    RuntimeException("Passed parameters are not ViewModelParameters type")

class DoesNotHaveContainerException :
    Exception("Does not implement HasContainer")