package com.nandaprasetio.inventintegrasitest

import com.nandaprasetio.inventintegrasitest.domain.entity.BasicResult
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.EmployeeLoginResult
import com.nandaprasetio.inventintegrasitest.domain.entity.Region

object MockHelper {
    fun mockProvinceSuccessBasicResult(): BasicResult<List<Region>> {
        return BasicResult(
            statusCode = "ERR-00-000",
            statusMessageIndonesian = null,
            statusMessageEnglish = null,
            value = listOf(
                Region("Aceh", "Aceh"),
                Region("Bali", "Bali"),
                Region("Banten", "Banten"),
                Region("Bengkulu", "Bengkulu"),
                Region("Daerah Istimewa Yogyakarta", "Daerah Istimewa Yogyakarta")
            )
        )
    }

    fun mockProvinceErrorBasicResult(): BasicResult<List<Region>> {
        return BasicResult(
            statusCode = "ERR-12-345",
            statusMessageIndonesian = "Test Error",
            statusMessageEnglish = "Error Test",
            value = listOf()
        )
    }

    fun mockCitySuccessBasicResultBasedAcehRegionId(): BasicResult<List<District>> {
        return BasicResult(
            statusCode = "ERR-00-000",
            statusMessageIndonesian = "Sukses",
            statusMessageEnglish = "",
            value = listOf(
                District("Aceh Barat", "Aceh Barat"),
                District("Aceh Barat Daya", "Aceh Barat Daya"),
                District("Aceh Besar", "Aceh Besar"),
                District("Aceh Jaya", "Aceh Jaya"),
                District("Aceh Selatan", "Aceh Selatan")
            )
        )
    }

    fun mockCityErrorBasicResultBasedAcehRegionId(): BasicResult<List<District>> {
        return BasicResult(
            statusCode = "ERR-12-345",
            statusMessageIndonesian = "Test Error",
            statusMessageEnglish = "Error Test",
            value = listOf()
        )
    }

    fun mockEmployeeLoginSuccessBasicResult(): BasicResult<EmployeeLoginResult> {
        return BasicResult(
            statusCode = "ERR-00-000",
            statusMessageIndonesian = "Sukses",
            statusMessageEnglish = "",
            value = EmployeeLoginResult(
                employeeId = "2060148",
                employeeName =  "nandha",
                employeeTypeId =  null,
                employeeTypeName =  null,
                branchId =  null,
                branchIdList =  null,
                entryDate =  null,
                outDate = null,
                createdBy =  null,
                createdDate =  null,
                updatedBy = null,
                updatedDate =  null,
                isActive = true,
                gender = null,
                email = "nandadwisubekti@gmail.com",
                phone =  "0812859126998",
                isRegister =  null,
                vehicleNo =  null,
                noRek =  null,
                password =  "e10adc3949ba59abbe56e057f20f883e",
                noKtp =  "12312312",
                imageKtp =  null,
                imageSkck =  null,
                firebaseToken =  null,
                deviceId =  null
            )
        )
    }

    fun mockEmployeeLoginErrorBasicResult(): BasicResult<EmployeeLoginResult> {
        return BasicResult(
            statusCode = "ERR-12-345",
            statusMessageIndonesian = "Tes Error",
            statusMessageEnglish = "Error Test",
            value = null
        )
    }
}