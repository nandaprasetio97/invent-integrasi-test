package com.nandaprasetio.inventintegrasitest.misc.ext

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import com.nandaprasetio.inventintegrasitest.misc.exception.BasicResultException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun<T> getHttpRequestResult(
    coroutineDispatcher: CoroutineDispatcher,
    onGetHttpRequestResult: suspend () -> T
): LoadDataResult<T> {
    return try {
        LoadDataResult.Success(withContext(coroutineDispatcher) { onGetHttpRequestResult() })
    } catch (e: Throwable) {
        LoadDataResult.Error(e)
    }
}

suspend fun<T> getHttpRequestBasicResult(
    coroutineDispatcher: CoroutineDispatcher,
    onGetHttpRequestResult: suspend () -> BasicResult<T>
): LoadDataResult<BasicResult<T>> {
    return when (val loadDataResult = getHttpRequestResult(coroutineDispatcher, onGetHttpRequestResult)) {
        is LoadDataResult.Success -> {
            val basicResult = loadDataResult.output
            if (basicResult.statusCode != "ERR-00-000") {
                LoadDataResult.Error(BasicResultException(basicResult))
            } else {
                loadDataResult
            }
        }
        is LoadDataResult.Error -> loadDataResult
        else -> throw IllegalStateException("Invalid result.")
    }
}