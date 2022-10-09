package com.example.kotlin.dal.repository

import com.example.kotlin.dal.entity.CreditReportEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CreditReportRepository: JpaRepository<CreditReportEntity, Long> {

    fun findByInn(inn: String): Optional<String>
    fun findByCreditInfoId(id: Int): Optional<String>
}