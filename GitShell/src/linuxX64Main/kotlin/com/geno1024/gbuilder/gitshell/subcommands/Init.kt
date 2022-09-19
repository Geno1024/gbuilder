package com.geno1024.gbuilder.gitshell.subcommands

import platform.posix.system

object Init
{
    operator fun invoke(parameters: List<String>)
    {
        system("git init --bare ${parameters[0]}")
    }
}
