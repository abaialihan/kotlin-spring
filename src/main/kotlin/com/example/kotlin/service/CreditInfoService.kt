package com.example.kotlin.service

import com.example.kotlin.dal.dto.CreditInfoDto


interface CreditInfoService {

    fun getCreditInfoId(inn: String): CreditInfoDto
    fun getCreditReport(id: Int): Any
    fun getCreditInfoReportFromBase(inn: String): Any
}