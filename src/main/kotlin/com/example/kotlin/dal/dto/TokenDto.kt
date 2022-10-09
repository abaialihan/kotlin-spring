package com.example.kotlin.dal.dto

import lombok.Getter

@Getter
data class TokenDto(
    var access_token: String,
    var subscriber: String
)
