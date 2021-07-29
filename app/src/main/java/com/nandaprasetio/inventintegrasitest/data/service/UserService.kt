package com.nandaprasetio.inventintegrasitest.data.service

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginResult
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("v1/login-employee")
    suspend fun loginAsEmployee(@Body employeeLoginRequestBody: EmployeeLoginRequestBody): BasicResult<EmployeeLoginResult>
}