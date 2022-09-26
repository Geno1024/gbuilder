package com.geno1024.gbuilder.ds

import com.geno1024.gbuilder.utils.FileUtils
import com.geno1024.gbuilder.utils.StringUtils.toMap
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toKStringFromUtf8
import platform.posix.getpwuid
import platform.posix.getuid

/**
 * Global config.
 *
 * Default [root] is home directory of current user.
 */
data class GlobalConfig(
    val root: String = getpwuid(getuid())?.pointed?.pw_dir?.toKStringFromUtf8()?:""
)
{
    val configFile: String = "$root/gbuilder.conf"

    val config = FileUtils.readFile(configFile)
        .toMap()

    val repositoryHome = config["repositoryHome"] ?: "$root/repository"
}
