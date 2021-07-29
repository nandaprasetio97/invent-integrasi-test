package com.nandaprasetio.inventintegrasitest.data.datasource.userdatasource

import com.nandaprasetio.inventintegrasitest.domain.entity.*

interface UserDataSource {
    suspend fun loginAsEmployee(employeeLoginRequestBody: EmployeeLoginRequestBody): BasicResult<EmployeeLoginResult>
}