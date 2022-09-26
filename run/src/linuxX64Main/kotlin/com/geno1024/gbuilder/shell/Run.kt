package com.geno1024.gbuilder.shell

import com.geno1024.gbuilder.utils.FileUtils
import platform.posix.chdir
import platform.posix.system

object Run
{
    fun main(args: Array<String>)
    {
        if (args.isNotEmpty())
        {
            val repo = args[0]
            val stepFile = args.getOrElse(1) { "gbuilder.step" }

            val tempDir = FileUtils.makeTemp("/tmp/gbuilder:$repo")
            chdir(tempDir)
            system("git init")

        }
        else
            println("run what?")
    }
}

fun main(args: Array<String>) = Run.main(args)
