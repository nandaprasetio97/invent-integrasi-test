package com.nandaprasetio.inventintegrasitest.domain.entity

import com.google.gson.annotations.SerializedName

class EmployeeLoginRequestBody(
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("password")
    val password: String?,
)