package com.nandaprasetio.inventintegrasitest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nandaprasetio.inventintegrasitest.CoroutineTestRule
import com.nandaprasetio.inventintegrasitest.MockHelper
import com.nandaprasetio.inventintegrasitest.data.datasource.userdatasource.UserDataSource
import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.DefaultUserRepository
import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.UserRepository
import com.nandaprasetio.inventintegrasitest.domain.entity.*
import com.nandaprasetio.inventintegrasitest.domain.usecase.loginasemployeeusecase.DefaultLoginAsEmployeeUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.loginasemployeeusecase.LoginAsEmployeeUseCase
import com.nandaprasetio.inventintegrasitest.ext.hasBasicResultException
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var userDataSource: UserDataSource
    private lateinit var userRepository: UserRepository

    // Use Case
    private lateinit var loginAsEmployeeUseCase: LoginAsEmployeeUseCase

    // Home View Model
    private lateinit var loginViewModel: LoginViewModel

    // Observer
    private lateinit var loginLiveDataObserver: Observer<LoadDataResult<BasicResult<EmployeeLoginResult>>>

    @Before
    fun init() {
        userDataSource = mock()
        userRepository = DefaultUserRepository(coroutineTestRule.testCoroutineDispatcher, userDataSource)
        loginAsEmployeeUseCase = DefaultLoginAsEmployeeUseCase(userRepository)
        loginViewModel = LoginViewModel(loginAsEmployeeUseCase)
        loginLiveDataObserver = mock()
    }

    @Test
    fun loginAsEmployee_isSuccess() {
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val employeeLoginRequestBody = EmployeeLoginRequestBody("0812859126998", "e10adc3949ba59abbe56e057f20f883e")
            val basicResult = MockHelper.mockEmployeeLoginSuccessBasicResult()
            whenever(userDataSource.loginAsEmployee(employeeLoginRequestBody)).thenReturn(basicResult)

            loginViewModel.loginLiveData.observeForever(loginLiveDataObserver)
            loginViewModel.loginAsEmployee(employeeLoginRequestBody)
            verify(loginLiveDataObserver).onChanged(LoadDataResult.Success(basicResult))
            loginViewModel.loginLiveData.removeObserver(loginLiveDataObserver)
        }
    }

    @Test
    fun loginAsEmployee_isError() {
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val throwable = IllegalStateException()
            val employeeLoginRequestBody = EmployeeLoginRequestBody("0812859126998", "e10adc3949ba59abbe56e057f20f883e")
            val basicResult = MockHelper.mockEmployeeLoginErrorBasicResult()
            whenever(userDataSource.loginAsEmployee(employeeLoginRequestBody))
                .thenThrow(throwable)
                .thenReturn(basicResult)

            // If throw throwable.
            loginViewModel.loginLiveData.observeForever(loginLiveDataObserver)
            loginViewModel.loginAsEmployee(employeeLoginRequestBody)
            verify(loginLiveDataObserver).onChanged(LoadDataResult.Error(throwable))

            // If because different error codes in basic result.
            loginViewModel.loginAsEmployee(employeeLoginRequestBody)
            hasBasicResultException(loginViewModel.loginLiveData.value as LoadDataResult<BasicResult<EmployeeLoginResult>>, basicResult)
            loginViewModel.loginLiveData.removeObserver(loginLiveDataObserver)
        }
    }
}