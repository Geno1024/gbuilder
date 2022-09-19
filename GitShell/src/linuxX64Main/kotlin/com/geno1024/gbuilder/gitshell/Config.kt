package com.geno1024.gbuilder.gitshell

import kotlinx.cinterop.*
import platform.posix.*

class Config(val file: String = "${getpwuid(getuid())?.pointed?.pw_dir?.toKStringFromUtf8()}/.gbuilder.conf")
{
    val contents = run {
        var c = ""
        val file = fopen(file, "r") ?: return@run "default"
        memScoped {
            val buffer = allocArray<ByteVar>(1024)
            while (fgets(buffer, 1024, file) != null)
                c += buffer.toKStringFromUtf8()
            fclose(file)
            c
        }
    }

    val configMap = contents.lines().filterNot { line ->
        listOf("#", "//").any { commentString -> // Sorry, I don't support multi line comment here.
            line.trim().startsWith(commentString)
        } or line.trim().isEmpty()
    }.associate { line -> // And, only one line a key-value pair allowed.
        line.split("=", limit = 2).run {
            get(0).trim() to get(1).trim()
        }
    }

    val repositoryHome = configMap["repositoryHome"]
}
