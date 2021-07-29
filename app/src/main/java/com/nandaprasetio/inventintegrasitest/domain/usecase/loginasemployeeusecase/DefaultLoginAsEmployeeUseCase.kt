package com.nandaprasetio.inventintegrasitest.domain.usecase.loginasemployeeusecase

import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.UserRepository
import com.nandaprasetio.inventintegrasitest.di.IODispatcher
import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginResult
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import javax.inject.Inject

class DefaultLoginAsEmployeeUseCase @Inject constructor(
    private val userRepository: UserRepository
): LoginAsEmployeeUseCase {
    override suspend fun loginAsEmployee(
        employeeLoginRequestBody: EmployeeLoginRequestBody
    ): LoadDataResult<BasicResult<EmployeeLoginResult>> {
        return userRepository.loginAsEmployee(employeeLoginRequestBody)
    }
}