package com.nandaprasetio.inventintegrasitest.data.repository.userrepository

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginResult
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult

interface UserRepository {
    suspend fun loginAsEmployee(employeeLoginRequestBody: EmployeeLoginRequestBody): LoadDataResult<BasicResult<EmployeeLoginResult>>
}