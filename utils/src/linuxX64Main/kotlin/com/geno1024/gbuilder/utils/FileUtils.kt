package com.geno1024.gbuilder.utils

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.*

object FileUtils
{
    fun readFile(path: String, bufferSize: Int = 4096): String
    {
        var content = ""
        val file = fopen(path, "r") ?: return ""
        memScoped {
            val buffer = allocArray<ByteVar>(bufferSize)
            while (fgets(buffer, bufferSize, file) != null)
            {
                content += buffer.toKString()
            }
            fclose(file)
        }
        return content
    }

    fun writeFile(path: String, content: String)
    {
        val file = fopen(path, "w")
        fputs(content, file)
        fflush(file)
    }
}
