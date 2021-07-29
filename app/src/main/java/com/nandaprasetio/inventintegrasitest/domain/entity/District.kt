package com.nandaprasetio.inventintegrasitest.domain.entity

import com.google.gson.annotations.SerializedName

data class District(
    @SerializedName("district_id")
    val id: String,
    @SerializedName("district_name")
    val name: String,
)