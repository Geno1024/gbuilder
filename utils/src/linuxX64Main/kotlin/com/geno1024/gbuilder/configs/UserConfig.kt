package com.geno1024.gbuilder.configs

import com.geno1024.gbuilder.utils.ConfigUtils
import com.geno1024.gbuilder.utils.FileUtils

@Suppress("unused")
class UserConfig(globalConfig: GlobalConfig, val username: String)
{
    val userDir = "${globalConfig.serverRoot}/user:$username"
    val configFile = "$userDir/config"
    val configFileContents = FileUtils.readFileContents(configFile)
    val configMap = ConfigUtils.contentToConfigMap(configFileContents)

    val privilege = Privilege.valueOf(configMap["privilege"]?:"Normal")

    enum class Privilege
    {
        Admin,
        Normal,
        Banned
    }
}
