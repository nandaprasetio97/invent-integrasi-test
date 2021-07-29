package com.nandaprasetio.inventintegrasitest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nandaprasetio.inventintegrasitest.CoroutineTestRule
import com.nandaprasetio.inventintegrasitest.MockHelper
import com.nandaprasetio.inventintegrasitest.data.datasource.itemdatasource.ItemDataSource
import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.DefaultItemRepository
import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.ItemRepository
import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import com.nandaprasetio.inventintegrasitest.domain.usecase.getcity.DefaultGetCityUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.getcity.GetCityUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.getprovince.DefaultGetProvinceUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.getprovince.GetProvinceUseCase
import com.nandaprasetio.inventintegrasitest.ext.hasBasicResultException
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import com.nandaprasetio.inventintegrasitest.misc.exception.BasicResultException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException
import java.sql.SQLException

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var itemDataSource: ItemDataSource
    private lateinit var itemRepository: ItemRepository

    // Use Case
    private lateinit var getCityUseCase: GetCityUseCase
    private lateinit var getProvinceUseCase: GetProvinceUseCase

    // Home View Model
    private lateinit var homeViewModel: HomeViewModel

    // Observer
    private lateinit var provinceLiveDataObserver: Observer<LoadDataResult<BasicResult<List<Region>>>>
    private lateinit var cityLiveDataObserver: Observer<LoadDataResult<BasicResult<List<District>>>>

    @Before
    fun init() {
        itemDataSource = mock()
        itemRepository = DefaultItemRepository(coroutineTestRule.testCoroutineDispatcher, itemDataSource)
        getCityUseCase = DefaultGetCityUseCase(itemRepository)
        getProvinceUseCase = DefaultGetProvinceUseCase(itemRepository)
        homeViewModel = HomeViewModel(getCityUseCase, getProvinceUseCase)
        provinceLiveDataObserver = mock()
        cityLiveDataObserver = mock()
    }

    @Test
    fun getProvince_isSuccess() {
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val basicResult = MockHelper.mockProvinceSuccessBasicResult()
            whenever(itemDataSource.getProvince()).thenReturn(basicResult)

            homeViewModel.provinceLiveData.observeForever(provinceLiveDataObserver)
            homeViewModel.getProvince()
            verify(provinceLiveDataObserver).onChanged(LoadDataResult.Success(basicResult))
            homeViewModel.provinceLiveData.removeObserver(provinceLiveDataObserver)
        }
    }

    @Test
    fun getProvince_isError() {
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val throwable = IllegalStateException()
            val basicResult = MockHelper.mockProvinceErrorBasicResult()
            whenever(itemDataSource.getProvince())
                .thenThrow(throwable)
                .thenReturn(basicResult)

            // If throw throwable.
            homeViewModel.provinceLiveData.observeForever(provinceLiveDataObserver)
            homeViewModel.getProvince()
            verify(provinceLiveDataObserver).onChanged(LoadDataResult.Error(throwable))

            // If because different error codes in basic result.
            homeViewModel.getProvince()
            hasBasicResultException(homeViewModel.provinceLiveData.value as LoadDataResult<BasicResult<List<Region>>>, basicResult)
            homeViewModel.provinceLiveData.removeObserver(provinceLiveDataObserver)
        }
    }

    @Test
    fun getCity_isSuccess() {
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val getCityRequestBody = GetCityRequestBody("Aceh")
            val basicResult = MockHelper.mockCitySuccessBasicResultBasedAcehRegionId()
            whenever(itemDataSource.getCity(getCityRequestBody)).thenReturn(basicResult)

            homeViewModel.cityLiveData.observeForever(cityLiveDataObserver)
            homeViewModel.getCity(getCityRequestBody)
            verify(cityLiveDataObserver).onChanged(LoadDataResult.Success(basicResult))
            homeViewModel.cityLiveData.removeObserver(cityLiveDataObserver)
        }
    }

    @Test
    fun getCity_isError() {
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val getCityRequestBody = GetCityRequestBody("Aceh")
            val basicResult = MockHelper.mockCityErrorBasicResultBasedAcehRegionId()
            val throwable = RuntimeException()
            whenever(itemDataSource.getCity(getCityRequestBody))
                .thenThrow(throwable)
                .thenReturn(basicResult)

            // If throw throwable.
            homeViewModel.cityLiveData.observeForever(cityLiveDataObserver)
            homeViewModel.getCity(getCityRequestBody)
            verify(cityLiveDataObserver).onChanged(LoadDataResult.Error(throwable))

            // If because different error codes in basic result.
            homeViewModel.getCity(getCityRequestBody)
            hasBasicResultException(homeViewModel.cityLiveData.value as LoadDataResult<BasicResult<List<District>>>, basicResult)
            homeViewModel.cityLiveData.removeObserver(cityLiveDataObserver)
        }
    }
}