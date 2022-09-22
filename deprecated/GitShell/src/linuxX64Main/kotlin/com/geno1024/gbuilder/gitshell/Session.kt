package com.geno1024.gbuilder.gitshell

@ThreadLocal
var user: User = User("user:anonymous")

object Session
{
    val globalConfig = GlobalConfig()
}
