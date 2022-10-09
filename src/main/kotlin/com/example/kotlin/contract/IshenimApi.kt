package com.example.kotlin.contract

import com.example.kotlin.dal.dto.CreditInfoDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping("/api/v1/")
interface IshenimApi {

    @PostMapping("/get-credit-info-id")
    fun getCreditInfoId(@RequestParam inn: String): CreditInfoDto

    @PostMapping("/get-credit-info-report")
    fun getCreditReport(@RequestParam id: Int): Any

    @PostMapping("/get-credit-info-from-base")
    fun getCreditReportFromBase(@RequestParam inn: String): Any
}