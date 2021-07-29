package com.nandaprasetio.inventintegrasitest.domain.entity

import com.google.gson.annotations.SerializedName

data class EmployeeLoginResult(
    @SerializedName("employee_id")
    val employeeId: String?,
    @SerializedName("employee_name")
    val employeeName: String?,
    @SerializedName("employee_type_id")
    val employeeTypeId: String?,
    @SerializedName("employee_type_name")
    val employeeTypeName: String?,
    @SerializedName("branch_id")
    val branchId: String?,
    @SerializedName("branch_id_list")
    val branchIdList: String?,
    @SerializedName("entry_date")
    val entryDate: String?,
    @SerializedName("out_date")
    val outDate: String?,
    @SerializedName("created_by")
    val createdBy: String?,
    @SerializedName("created_date")
    val createdDate: String?,
    @SerializedName("updated_by")
    val updatedBy: String?,
    @SerializedName("updated_date")
    val updatedDate: String?,
    @SerializedName("is_active")
    val isActive: Boolean?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("is_register")
    val isRegister: Boolean?,
    @SerializedName("vehicle_no")
    val vehicleNo: String?,
    @SerializedName("no_rek")
    val noRek: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("no_ktp")
    val noKtp: String?,
    @SerializedName("image_ktp")
    val imageKtp: String?,
    @SerializedName("image_skck")
    val imageSkck: String?,
    @SerializedName("firebase_token")
    val firebaseToken: String?,
    @SerializedName("device_id")
    val deviceId: String?,
)