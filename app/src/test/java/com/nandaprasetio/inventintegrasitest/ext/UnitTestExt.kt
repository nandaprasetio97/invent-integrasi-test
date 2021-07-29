package com.nandaprasetio.inventintegrasitest.ext

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import com.nandaprasetio.inventintegrasitest.misc.exception.BasicResultException
import org.junit.Assert

suspend fun<T> testRepositoryConnection(
    onGetRepositoryData: suspend () -> LoadDataResult<T>
) {
    val loadDataResult = onGetRepositoryData()
    var message: String? = null
    val isSuccessLoadDataResult = when (loadDataResult) {
        is LoadDataResult.Success -> true
        is LoadDataResult.Error -> { message = loadDataResult.e.toString(); false }
    }
    Assert.assertTrue(message, isSuccessLoadDataResult)
}

fun<T> hasBasicResultException(
    loadDataResult: LoadDataResult<BasicResult<T>>,
    basicResult: BasicResult<T>
): Boolean {
    return loadDataResult.let {
        when (it) {
            is LoadDataResult.Error -> {
                when (val e = it.e) {
                    is BasicResultException -> e.basicResult == basicResult
                    else -> false
                }
            }
            is LoadDataResult.Success -> false
        }
    }
}