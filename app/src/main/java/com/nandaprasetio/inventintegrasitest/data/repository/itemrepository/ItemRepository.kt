package com.nandaprasetio.inventintegrasitest.data.repository.itemrepository

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult

interface ItemRepository {
    suspend fun getProvince(): LoadDataResult<BasicResult<List<Region>>>
    suspend fun getCity(getCityRequestBody: GetCityRequestBody): LoadDataResult<BasicResult<List<District>>>
}