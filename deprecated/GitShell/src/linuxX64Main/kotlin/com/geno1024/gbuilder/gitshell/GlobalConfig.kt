package com.geno1024.gbuilder.gitshell

import kotlinx.cinterop.*
import platform.posix.*

class GlobalConfig(file: String = "${getpwuid(getuid())?.pointed?.pw_dir?.toKStringFromUtf8()}/.gbuilder.conf")
{
    val contents = Utils.readFileContents(file)
    val configMap = Utils.contentToConfigMap(contents)

    val serverRoot = configMap["serverRoot"]
}
