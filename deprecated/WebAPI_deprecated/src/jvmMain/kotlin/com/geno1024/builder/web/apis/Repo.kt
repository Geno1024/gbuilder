package com.geno1024.builder.web.apis

import com.geno1024.builder.web.AnAPI
import com.geno1024.builder.web.Constants
import com.geno1024.builder.web.structures.ErrorMessage
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
            responseBody.write(ErrorMessage("Only GET allowed.").toJSONBytes())
            close()
        }

        if (url.substringAfter("/api/repo/").count { it == '/' } < 1)
        {
            sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0)
            responseBody.write(ErrorMessage("Parameter missing: repo", "Specify repo in URL with this format: /api/repo/[owner]/[repo].").toJSONBytes())
            close()
        }
        val (owner, repo) = url.substringAfter("/api/repo/").split("/", limit = 2)
    }
)
