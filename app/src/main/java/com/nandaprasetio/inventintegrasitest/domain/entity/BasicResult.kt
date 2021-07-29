package com.nandaprasetio.inventintegrasitest.domain.entity

import com.google.gson.annotations.SerializedName

data class BasicResult<T>(
    @SerializedName("status_code")
    val statusCode: String,
    @SerializedName("status_message_ind")
    val statusMessageIndonesian: String?,
    @SerializedName("status_message_eng")
    val statusMessageEnglish: String?,
    @SerializedName("value")
    val value: T?,
)