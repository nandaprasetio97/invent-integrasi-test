package com.nandaprasetio.inventintegrasitest.domain.usecase.getcity

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult

interface GetCityUseCase {
    suspend fun getCity(getCityRequestBody: GetCityRequestBody): LoadDataResult<BasicResult<List<District>>>
}