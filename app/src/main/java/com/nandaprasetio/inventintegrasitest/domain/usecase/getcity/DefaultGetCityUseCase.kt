package com.nandaprasetio.inventintegrasitest.domain.usecase.getcity

import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.ItemRepository
import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import javax.inject.Inject

class DefaultGetCityUseCase @Inject constructor(
    private val itemRepository: ItemRepository
): GetCityUseCase {
    override suspend fun getCity(getCityRequestBody: GetCityRequestBody): LoadDataResult<BasicResult<List<District>>> {
        return itemRepository.getCity(getCityRequestBody)
    }
}