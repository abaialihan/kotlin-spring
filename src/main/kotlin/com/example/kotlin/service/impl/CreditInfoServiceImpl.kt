package com.example.kotlin.service.impl

import com.example.kotlin.configuration.CertificateConfig
import com.example.kotlin.dal.dto.CreditInfoDto
import com.example.kotlin.service.CreditInfoService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CreditInfoServiceImpl: CreditInfoService {

    val certificateConfig = CertificateConfig()

    override fun getCreditInfoId(inn: String): CreditInfoDto {
        val creditInfoDto = certificateConfig.getCreditInfoId(inn)


    }

    override fun getCreditReport(id: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getCreditInfoReportFromBase(inn: String): Any {
        TODO("Not yet implemented")
    }
}