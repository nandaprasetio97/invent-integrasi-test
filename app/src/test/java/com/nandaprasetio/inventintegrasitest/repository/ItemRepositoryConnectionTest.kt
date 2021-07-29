package com.nandaprasetio.inventintegrasitest.repository

import com.nandaprasetio.inventintegrasitest.CoroutineTestRule
import com.nandaprasetio.inventintegrasitest.data.datasource.itemdatasource.DefaultItemDataSource
import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.DefaultItemRepository
import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.ItemRepository
import com.nandaprasetio.inventintegrasitest.di.NetworkModule
import com.nandaprasetio.inventintegrasitest.di.ServiceModule
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.ext.testRepositoryConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemRepositoryConnectionTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var itemRepository: ItemRepository

    @Before
    fun init() {
        itemRepository = DefaultItemRepository(
            coroutineTestRule.testCoroutineDispatcher,
            DefaultItemDataSource(
                ServiceModule().provideItemService(NetworkModule().provideRetrofit())
            )
        )
    }

    @Test
    fun getProvince_isSuccess() {
        runBlocking {
            testRepositoryConnection { itemRepository.getProvince() }
        }
    }

    @Test
    fun getCity_isSuccess() {
        runBlocking {
            val getCityRequestBody = GetCityRequestBody("Aceh")
            testRepositoryConnection { itemRepository.getCity(getCityRequestBody) }
        }
    }
}