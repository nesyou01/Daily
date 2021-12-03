package com.nesyou.daily.core.domain.models

import java.util.*

data class User(
    val name: String,
    val cover: String?,
    val updateAt: Date,
)
