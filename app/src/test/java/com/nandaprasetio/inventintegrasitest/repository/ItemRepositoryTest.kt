package com.nandaprasetio.inventintegrasitest.repository

import com.nandaprasetio.inventintegrasitest.CoroutineTestRule
import com.nandaprasetio.inventintegrasitest.MockHelper
import com.nandaprasetio.inventintegrasitest.data.datasource.itemdatasource.ItemDataSource
import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.DefaultItemRepository
import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.ItemRepository
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.ext.hasBasicResultException
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ItemRepositoryTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var itemDataSource: ItemDataSource
    private lateinit var itemRepository: ItemRepository

    @Before
    fun init() {
        itemDataSource = mock()
        itemRepository = DefaultItemRepository(coroutineTestRule.testCoroutineDispatcher, itemDataSource)
    }

    @Test
    fun getProvince_isSuccess() {
        runBlocking {
            val basicResult = MockHelper.mockProvinceSuccessBasicResult()
            whenever(itemDataSource.getProvince()).thenReturn(basicResult)
            val provinceLoadDataResult = itemRepository.getProvince()
            Assert.assertEquals(provinceLoadDataResult, LoadDataResult.Success(basicResult))
        }
    }

    @Test
    fun getProvince_isError() {
        runBlocking {
            val throwable = IllegalStateException()
            val basicResult = MockHelper.mockProvinceErrorBasicResult()
            whenever(itemDataSource.getProvince())
                .thenThrow(throwable)
                .thenReturn(basicResult)

            // If throw throwable.
            var provinceLoadDataResult = itemRepository.getProvince()
            Assert.assertEquals(provinceLoadDataResult, LoadDataResult.Error<Throwable>(throwable))

            // If because different error codes in basic result.
            provinceLoadDataResult = itemRepository.getProvince()
            Assert.assertTrue(hasBasicResultException(provinceLoadDataResult, basicResult))
        }
    }

    @Test
    fun getCity_isSuccess() {
        runBlocking {
            val getCityRequestBody = GetCityRequestBody("Aceh")
            val basicResult = MockHelper.mockCitySuccessBasicResultBasedAcehRegionId()
            whenever(itemDataSource.getCity(getCityRequestBody)).thenReturn(basicResult)
            val cityLoadDataResult = itemRepository.getCity(getCityRequestBody)
            Assert.assertEquals(cityLoadDataResult, LoadDataResult.Success(basicResult))
        }
    }

    @Test
    fun getCity_isError() {
        runBlocking {
            val getCityRequestBody = GetCityRequestBody("Aceh")
            val throwable = IllegalStateException()
            val basicResult = MockHelper.mockCityErrorBasicResultBasedAcehRegionId()
            whenever(itemDataSource.getCity(getCityRequestBody))
                .thenThrow(throwable)
                .thenReturn(basicResult)

            // If throw throwable.
            var cityLoadDataResult = itemRepository.getCity(getCityRequestBody)
            Assert.assertEquals(cityLoadDataResult, LoadDataResult.Error<Throwable>(throwable))

            // If because different error codes in basic result.
            cityLoadDataResult = itemRepository.getCity(getCityRequestBody)
            Assert.assertTrue(hasBasicResultException(cityLoadDataResult, basicResult))
        }
    }
}