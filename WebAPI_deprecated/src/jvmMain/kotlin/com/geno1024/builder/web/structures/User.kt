package com.geno1024.builder.web.structures

import java.util.*

data class User(
    val username: String,
    val id: Long,
    val type: UserType,

    val name: String,
    val email: String,

    val created: Date,
    val updated: Date,
    val repos: Long
)
