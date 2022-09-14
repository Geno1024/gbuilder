package com.geno1024.builder.web.apis

import com.alibaba.fastjson2.JSONObject
import com.geno1024.builder.web.AnAPI
import com.geno1024.builder.web.Constants
import java.net.HttpURLConnection

class Repo : AnAPI(
    urlPrefix = "/api/repo",
    handler = {
        val method = requestMethod
        val url = requestURI.path
        val query = requestURI.query?.split('&')?.associate { it.split('=').run { get(0) to getOrElse(1) { "true" } } }?: emptyMap()
        val headers = requestHeaders.toMap()
        val body = requestBody.readBytes().toString()

        setAttribute("Server", Constants.serverName)

        if (method != "GET")
        {
            sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0)
            responseBody.write(JSONObject.toJSONString(Error("Only GET allowed.")).toByteArray(Constants.charset))
            close()
        }

        println(url.substringAfter("/api/repo"))
    }
)
