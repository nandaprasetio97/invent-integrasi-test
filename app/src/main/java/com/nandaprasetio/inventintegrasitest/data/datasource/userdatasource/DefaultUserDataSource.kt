package com.nandaprasetio.inventintegrasitest.data.datasource.userdatasource

import com.nandaprasetio.inventintegrasitest.data.service.UserService
import com.nandaprasetio.inventintegrasitest.domain.entity.*
import javax.inject.Inject

class DefaultUserDataSource @Inject constructor(
    private val userService: UserService
): UserDataSource {
    override suspend fun loginAsEmployee(employeeLoginRequestBody: EmployeeLoginRequestBody): BasicResult<EmployeeLoginResult> {
        return userService.loginAsEmployee(employeeLoginRequestBody)
    }
}