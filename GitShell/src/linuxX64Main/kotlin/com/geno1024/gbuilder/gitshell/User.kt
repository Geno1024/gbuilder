package com.geno1024.gbuilder.gitshell

class User(val username: String)
{
    val userDir = "${Session.globalConfig.serverRoot}/user:$username"
    val configFile = "$userDir/config"
    val configFileContents = Utils.readFileContents(configFile)
    val configMap = Utils.contentToConfigMap(configFileContents)

    val privilege = Privilege.valueOf(configMap["privilege"]?:"Normal")


    enum class Privilege
    {
        Admin,
        Normal,
        Banned
    }
}
