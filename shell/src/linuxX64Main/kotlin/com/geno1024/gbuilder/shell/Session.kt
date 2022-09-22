package com.geno1024.gbuilder.shell

import com.geno1024.gbuilder.configs.GlobalConfig
import com.geno1024.gbuilder.configs.UserConfig

var globalConfig = GlobalConfig()

@ThreadLocal
var user: UserConfig = UserConfig(globalConfig, "user:anonymous")
