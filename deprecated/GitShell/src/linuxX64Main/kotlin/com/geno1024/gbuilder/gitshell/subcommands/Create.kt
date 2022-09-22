package com.geno1024.gbuilder.gitshell.subcommands

import com.geno1024.gbuilder.gitshell.Session
import com.geno1024.gbuilder.gitshell.User
import com.geno1024.gbuilder.gitshell.user
import platform.posix.*

object Create
{
    fun help()
    {
        println("Available actions for \"create\" are:")
        println("repo \$reponame...")
        println("repository \$reponame...")
        println("user \$username [\$privilege]")
    }

    operator fun invoke(parameters: List<String>) = if (parameters.isEmpty())
        help()
    else when (parameters[0])
    {
        "repo", "repository" -> repository(parameters.drop(1))
        "user" -> user(parameters.drop(1))
        else -> help()
    }

    fun repository(args: List<String>) = args.forEach { arg ->
        system("git init --bare ${user.userDir}/$arg")
    }

    fun user(args: List<String>)
    {
        if (user.privilege != User.Privilege.Admin)
        {
            println("Only admin can add user!")
            return
        }
        val username = args.getOrElse(0) { println("Missing username"); return }
        val privilege = User.Privilege.valueOf(args.getOrNull(1)?:"Normal")
        mkdir("${Session.globalConfig.serverRoot}/username:$username", (S_IRWXU or S_IRGRP or S_IROTH).toUInt())
    }
}
