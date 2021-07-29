package com.nandaprasetio.inventintegrasitest.data.repository.itemrepository

import com.nandaprasetio.inventintegrasitest.data.datasource.itemdatasource.ItemDataSource
import com.nandaprasetio.inventintegrasitest.di.IODispatcher
import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import com.nandaprasetio.inventintegrasitest.misc.ext.getHttpRequestBasicResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DefaultItemRepository @Inject constructor(
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val itemDataSource: ItemDataSource
): ItemRepository {
    override suspend fun getProvince(): LoadDataResult<BasicResult<List<Region>>> {
        return getHttpRequestBasicResult(coroutineDispatcher) { itemDataSource.getProvince() }
    }

    override suspend fun getCity(getCityRequestBody: GetCityRequestBody): LoadDataResult<BasicResult<List<District>>> {
        return getHttpRequestBasicResult(coroutineDispatcher) { itemDataSource.getCity(getCityRequestBody) }
    }
}