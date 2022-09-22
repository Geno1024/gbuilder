package com.geno1024.builder.web

import com.geno1024.builder.web.apis.Repo
import com.geno1024.builder.web.apis.Users

object Constants
{
    val serverName = "GBuilder/0.0"

    val port = 8080

    val charset = Charsets.UTF_8

    val repoLocation = "/home/geno1024/repositories"

    val apiList = listOf(
        Repo::class,
        Users::class
    )
}
