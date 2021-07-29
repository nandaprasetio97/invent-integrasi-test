package com.nandaprasetio.inventintegrasitest.domain.usecase.getprovince

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult

interface GetProvinceUseCase {
    suspend fun getProvince(): LoadDataResult<BasicResult<List<Region>>>
}