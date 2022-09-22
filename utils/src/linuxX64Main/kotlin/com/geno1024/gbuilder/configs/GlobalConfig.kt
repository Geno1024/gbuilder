package com.geno1024.gbuilder.configs

import com.geno1024.gbuilder.utils.ConfigUtils
import com.geno1024.gbuilder.utils.FileUtils
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toKStringFromUtf8
import platform.posix.getpwuid
import platform.posix.getuid

class GlobalConfig(file: String = "${getpwuid(getuid())?.pointed?.pw_dir?.toKStringFromUtf8()}/.gbuilder.conf")
{
    val executableLocation = FileUtils.getExecutableLocation().substringBeforeLast('/') + '/'
    val contents = FileUtils.readFileContents(file)
    val configMap = ConfigUtils.contentToConfigMap(contents)

    val serverRoot = configMap["serverRoot"]
}
