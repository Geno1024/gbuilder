package com.geno1024.gbuilder.builderserver

object Builder
{
    fun main(args: Array<String>)
    {
        EnvironmentManager.initUbuntu("22.04")
    }
}

fun main(args: Array<String>) = Builder.main(args)
