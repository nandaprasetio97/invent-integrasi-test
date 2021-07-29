package com.nandaprasetio.inventintegrasitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import com.nandaprasetio.inventintegrasitest.domain.usecase.getcity.GetCityUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.getprovince.GetProvinceUseCase
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCityUseCase: GetCityUseCase,
    private val getProvinceUseCase: GetProvinceUseCase
): ViewModel() {
    // Province
    private val provinceMutableLiveData: MutableLiveData<LoadDataResult<BasicResult<List<Region>>>> = MutableLiveData()
    val provinceLiveData: LiveData<LoadDataResult<BasicResult<List<Region>>>> = provinceMutableLiveData

    // City
    private val cityMutableLiveData: MutableLiveData<LoadDataResult<BasicResult<List<District>>>> = MutableLiveData()
    val cityLiveData: LiveData<LoadDataResult<BasicResult<List<District>>>> = cityMutableLiveData

    private var hasLoadingProvince: Boolean = false

    fun loadProvince() {
        if (!hasLoadingProvince) {
            hasLoadingProvince = true
            getProvince()
        }
    }

    fun getProvince() {
        provinceMutableLiveData.value = LoadDataResult.Loading()
        viewModelScope.launch {
            provinceMutableLiveData.postValue(getProvinceUseCase.getProvince())
        }
    }

    fun getCity(getCityRequestBody: GetCityRequestBody) {
        provinceMutableLiveData.value = LoadDataResult.Loading()
        viewModelScope.launch {
            cityMutableLiveData.postValue(getCityUseCase.getCity(getCityRequestBody))
        }
    }
}