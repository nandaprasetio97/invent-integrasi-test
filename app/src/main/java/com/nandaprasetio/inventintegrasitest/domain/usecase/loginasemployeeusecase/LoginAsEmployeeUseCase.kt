package com.nandaprasetio.inventintegrasitest.domain.usecase.loginasemployeeusecase

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginResult
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult

interface LoginAsEmployeeUseCase {
    suspend fun loginAsEmployee(
        employeeLoginRequestBody: EmployeeLoginRequestBody
    ): LoadDataResult<BasicResult<EmployeeLoginResult>>
}