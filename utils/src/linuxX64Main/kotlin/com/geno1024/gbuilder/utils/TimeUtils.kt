package com.geno1024.gbuilder.utils

import kotlinx.cinterop.*
import platform.posix.*

object TimeUtils
{
    fun getCurrentTime() = memScoped {
        val result = allocArray<ByteVar>(200)
        val t = alloc(time(null))
        val tm = localtime(t.ptr)
        strftime(result, 200, "%s", tm)
        result.toKStringFromUtf8().toLongOrNull()?:-1
    }
}
