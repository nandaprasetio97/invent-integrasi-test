package com.nandaprasetio.inventintegrasitest.data.service

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import retrofit2.http.Body
import retrofit2.http.POST

interface ItemService {
    @POST("bypass/get-provinsi")
    suspend fun getProvince(): BasicResult<List<Region>>

    @POST("bypass/get-kota")
    suspend fun getCity(@Body getCityRequestBody: GetCityRequestBody): BasicResult<List<District>>
}