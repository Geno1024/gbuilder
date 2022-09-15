package com.geno1024.builder.web.apis

import com.geno1024.builder.web.AnAPI
import com.geno1024.builder.web.Constants
import com.geno1024.builder.web.structures.ErrorMessage
import java.io.File
import java.net.HttpURLConnection

class Users : AnAPI(
    urlPrefix = "/api/users",
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

        if (url.substringAfter("/api/users").count { it == '/' } < 1)
        {
            sendResponseHeaders(HttpURLConnection.HTTP_OK, 0)
            responseBody.write(
            File(Constants.repoLocation).listFiles().toList().toString().toByteArray(Constants.charset)
            )
//            responseBody.write(ErrorMessage("Parameter missing: repo", "Specify repo in URL with this format: /api/repo/[owner]/[repo].").toJSONBytes())
            close()
        }
    }
)
{
}
