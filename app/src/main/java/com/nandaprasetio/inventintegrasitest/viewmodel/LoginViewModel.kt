package com.nandaprasetio.inventintegrasitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaprasetio.inventintegrasitest.domain.entity.*
import com.nandaprasetio.inventintegrasitest.domain.usecase.loginasemployeeusecase.LoginAsEmployeeUseCase
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginAsEmployeeUseCase: LoginAsEmployeeUseCase
): ViewModel() {
    // Login As Employee
    private val loginMutableLiveData: MutableLiveData<LoadDataResult<BasicResult<EmployeeLoginResult>>> = MutableLiveData()
    val loginLiveData: LiveData<LoadDataResult<BasicResult<EmployeeLoginResult>>> = loginMutableLiveData

    fun loginAsEmployee(employeeLoginRequestBody: EmployeeLoginRequestBody) {
        viewModelScope.launch {
            loginMutableLiveData.postValue(loginAsEmployeeUseCase.loginAsEmployee(employeeLoginRequestBody))
        }
    }
}