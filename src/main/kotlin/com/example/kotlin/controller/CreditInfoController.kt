package com.example.kotlin.controller

import com.example.kotlin.contract.IshenimApi
import com.example.kotlin.dal.dto.CreditInfoDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class CreditInfoController: IshenimApi{

    override fun getCreditInfoId(inn: String): CreditInfoDto {
        TODO("Not yet implemented")
    }

    override fun getCreditReport(id: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getCreditReportFromBase(inn: String): Any {
        TODO("Not yet implemented")
    }
}