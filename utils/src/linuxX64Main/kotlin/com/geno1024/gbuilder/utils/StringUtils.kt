package com.geno1024.gbuilder.utils

object StringUtils
{
    fun String.toMap(kvSeparator: String = "=", lineSeparator: String = "\n") = splitToSequence(lineSeparator)
        .associate {
            it.split(kvSeparator, limit = 2).let { (key, value) ->
                // possible leak
                key.trim() to value.trim()
            }
        }
}
