package com.nandaprasetio.inventintegrasitest.data.repository.userrepository

import com.nandaprasetio.inventintegrasitest.data.datasource.userdatasource.UserDataSource
import com.nandaprasetio.inventintegrasitest.di.IODispatcher
import com.nandaprasetio.inventintegrasitest.domain.entity.*
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import com.nandaprasetio.inventintegrasitest.misc.ext.getHttpRequestBasicResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun loginAsEmployee(employeeLoginRequestBody: EmployeeLoginRequestBody): LoadDataResult<BasicResult<EmployeeLoginResult>> {
        return getHttpRequestBasicResult(coroutineDispatcher) { userDataSource.loginAsEmployee(employeeLoginRequestBody) }
    }
}