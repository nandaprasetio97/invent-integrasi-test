package com.nandaprasetio.inventintegrasitest.domain.entity

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("region_id")
    val id: String,
    @SerializedName("region_name")
    val name: String
)