package com.nandaprasetio.inventintegrasitest.misc

sealed class LoadDataResult<T>(open var tag: Any? = null) {
    data class Success<T>(val output: T, override var tag: Any? = null): LoadDataResult<T>()
    data class Error<T>(val e: Throwable, override var tag: Any? = null): LoadDataResult<T>()
    data class Loading<T>(override var tag: Any? = null): LoadDataResult<T>()
}