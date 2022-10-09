package com.example.kotlin.dal.dto

import lombok.Getter

@Getter
data class RequestDto(
    var username: String,
    var password: String
)