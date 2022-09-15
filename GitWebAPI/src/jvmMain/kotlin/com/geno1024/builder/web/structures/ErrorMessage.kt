package com.geno1024.builder.web.structures

import com.alibaba.fastjson2.JSONObject
import com.geno1024.builder.web.Constants

data class ErrorMessage(
    val message: String,
    val possibleSolution: String = ""
) {
    fun toJSONBytes() = JSONObject.toJSONString(this).toByteArray(Constants.charset)
}
