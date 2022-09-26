package com.geno1024.gbuilder.utils

import kotlinx.cinterop.*
import platform.posix.*

object FileUtils
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

    fun getExecutableLocation(): String = memScoped {
        with (allocArray<ByteVar>(PATH_MAX)) {
            readlink("/proc/self/exe", this, PATH_MAX)
            toKStringFromUtf8()
        }
    }

    fun makeTemp(prefix: String) = memScoped {
        val tempName = "$prefix-XXXXXX"
        val tempDir = allocArray<ByteVar>(tempName.length) { value = tempName[it].code.toByte() }
        mkdtemp(tempDir)
        tempDir.toKStringFromUtf8()
    }
}
