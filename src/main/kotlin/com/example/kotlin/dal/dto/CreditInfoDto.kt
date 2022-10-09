package com.example.kotlin.dal.dto

import lombok.*

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
data class CreditInfoDto(
    var creditinfoId: Int,
    var lastInquiryDate: String,
    var isEntitledToFreeCreditReport: Boolean,
    var status: String
)