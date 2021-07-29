package com.nandaprasetio.inventintegrasitest.domain.usecase.getprovince

import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.ItemRepository
import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import javax.inject.Inject

class DefaultGetProvinceUseCase @Inject constructor(
    private val itemRepository: ItemRepository
): GetProvinceUseCase {
    override suspend fun getProvince(): LoadDataResult<BasicResult<List<Region>>> {
        return itemRepository.getProvince()
    }
}