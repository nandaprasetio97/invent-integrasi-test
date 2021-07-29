package com.nandaprasetio.inventintegrasitest.data.datasource.itemdatasource

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.Region

interface ItemDataSource {
    suspend fun getProvince(): BasicResult<List<Region>>
    suspend fun getCity(getCityRequestBody: GetCityRequestBody): BasicResult<List<District>>
}