package com.geno1024.gbuilder.gitshell

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKStringFromUtf8
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen

object Utils
{
    fun readFileContents(path: String): String
    {
        var content = ""
        val file = fopen(path, "r") ?: return ""
        memScoped {
            val buffer = allocArray<ByteVar>(1024)
            while (fgets(buffer, 1024, file) != null)
                content += buffer.toKStringFromUtf8()
            fclose(file)
        }
        return content
    }

    fun contentToConfigMap(
        content: String,
        kvDelimiter: String = "=",
        commentPrefixes: List<String> = listOf("#", "//")
    ) = content.lines()
        .map { line ->
            commentPrefixes.fold(line) { l, commentPrefix ->
                l.substringBefore(commentPrefix)
            }
        }.filter { line ->
            line.isNotBlank()
        }.associate { line -> // And, only one line a key-value pair allowed.
            line.split(kvDelimiter, limit = 2).run {
                get(0).trim() to get(1).trim()
            }
        }
}
