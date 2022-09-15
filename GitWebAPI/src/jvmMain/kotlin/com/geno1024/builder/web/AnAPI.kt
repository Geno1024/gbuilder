package com.geno1024.builder.web

import com.alibaba.fastjson2.JSONObject
import com.sun.net.httpserver.HttpExchange

abstract class AnAPI(
    val urlPrefix: String,
    val handler: HttpExchange.() -> Unit
) {
    data class ErrorMessage(
        val message: String,
        val possibleSolution: String = ""
    ) {
        fun toJSONBytes() = JSONObject.toJSONString(this).toByteArray(Constants.charset)
    }
}
