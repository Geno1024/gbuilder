package com.geno1024.builder.web

import com.sun.net.httpserver.HttpExchange

abstract class AnAPI(
    val urlPrefix: String,
    val handler: HttpExchange.() -> Unit
)
