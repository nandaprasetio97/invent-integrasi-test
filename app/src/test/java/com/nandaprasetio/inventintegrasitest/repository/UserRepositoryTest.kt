package com.nandaprasetio.inventintegrasitest.repository

import com.nandaprasetio.inventintegrasitest.CoroutineTestRule
import com.nandaprasetio.inventintegrasitest.MockHelper
import com.nandaprasetio.inventintegrasitest.data.datasource.userdatasource.UserDataSource
import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.DefaultUserRepository
import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.UserRepository
import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginRequestBody
import com.nandaprasetio.inventintegrasitest.ext.hasBasicResultException
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import com.nandaprasetio.inventintegrasitest.misc.exception.BasicResultException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UserRepositoryTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var userDataSource: UserDataSource
    private lateinit var userRepository: UserRepository

    @Before
    fun init() {
        userDataSource = mock()
        userRepository = DefaultUserRepository(coroutineTestRule.testCoroutineDispatcher, userDataSource)
    }

    @Test
    fun login_isSuccess() {
        runBlocking {
            val employeeLoginRequestBody = EmployeeLoginRequestBody("0812859126998", "e10adc3949ba59abbe56e057f20f883e")
            val basicResult = MockHelper.mockEmployeeLoginSuccessBasicResult()
            whenever(userDataSource.loginAsEmployee(employeeLoginRequestBody)).thenReturn(basicResult)
            val employeeLoginResult = userRepository.loginAsEmployee(employeeLoginRequestBody)
            Assert.assertEquals(employeeLoginResult, LoadDataResult.Success(basicResult))
        }
    }

    @Test
    fun login_isError() {
        runBlocking {
            val employeeLoginRequestBody = EmployeeLoginRequestBody("0812859126998", "e10adc3949ba59abbe56e057f20f883e")
            val throwable = IllegalStateException()
            val basicResult = MockHelper.mockEmployeeLoginErrorBasicResult()
            whenever(userDataSource.loginAsEmployee(employeeLoginRequestBody))
                .thenThrow(throwable)
                .thenReturn(basicResult)

            // If throw throwable.
            var provinceLoadDataResult = userRepository.loginAsEmployee(employeeLoginRequestBody)
            Assert.assertEquals(provinceLoadDataResult, LoadDataResult.Error<Throwable>(throwable))

            // If because different error codes in basic result.
            provinceLoadDataResult = userRepository.loginAsEmployee(employeeLoginRequestBody)
            Assert.assertTrue(hasBasicResultException(provinceLoadDataResult, basicResult))
        }
    }
}