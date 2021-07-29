package com.nandaprasetio.inventintegrasitest.data.datasource.itemdatasource

import com.nandaprasetio.inventintegrasitest.data.service.ItemService
import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import javax.inject.Inject

class DefaultItemDataSource @Inject constructor(
    private val itemService: ItemService
): ItemDataSource {
    override suspend fun getProvince(): BasicResult<List<Region>> {
        return itemService.getProvince()
    }

    override suspend fun getCity(getCityRequestBody: GetCityRequestBody): BasicResult<List<District>> {
        return itemService.getCity(getCityRequestBody)
    }
}