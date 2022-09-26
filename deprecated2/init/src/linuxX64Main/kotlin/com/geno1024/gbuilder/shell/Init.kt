package com.geno1024.gbuilder.shell

import platform.posix.system

object Init
{
    fun main(args: Array<String>)
    {
        if (args.isNotEmpty())
            system("git init --bare ${args[0]}")
        else
            println("init what?")
    }
}

fun main(args: Array<String>) = Init.main(args)
