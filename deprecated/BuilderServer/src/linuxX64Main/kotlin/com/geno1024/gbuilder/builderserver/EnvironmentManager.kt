package com.geno1024.gbuilder.builderserver

import platform.posix.*

object EnvironmentManager
{
    val ubuntu = mapOf(
        "18.04" to "bionic",
        "20.04" to "focal",
        "22.04" to "jammy"
    )

    val mirror = "http://mirrors.tuna.tsinghua.edu.cn/ubuntu"

    fun initUbuntu(version: String)
    {
        mkdir("ubuntu-$version", (S_IRWXU or S_IRGRP or S_IROTH).toUInt())
        system("fakeroot debootstrap ${ubuntu[version]} ubuntu-$version $mirror")
    }

    fun init()
    {

    }
}
