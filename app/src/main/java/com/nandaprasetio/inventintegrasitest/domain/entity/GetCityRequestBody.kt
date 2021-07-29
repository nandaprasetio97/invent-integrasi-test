package com.nandaprasetio.inventintegrasitest.domain.entity

import com.google.gson.annotations.SerializedName

data class GetCityRequestBody(
    @SerializedName("region_id")
    val regionId: String
)