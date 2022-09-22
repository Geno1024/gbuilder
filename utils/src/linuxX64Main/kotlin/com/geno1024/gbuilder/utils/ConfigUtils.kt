package com.geno1024.gbuilder.utils

object ConfigUtils
{
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
