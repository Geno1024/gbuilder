package com.geno1024.gbuilder.shell

import com.geno1024.gbuilder.configs.UserConfig
import platform.posix.fopen
import platform.posix.system
import kotlin.system.exitProcess

object ShellMain
{
    fun parse(input: List<String>)
    {
        when (input[0])
        {
            "quit", "exit" -> exitProcess(0)
            else ->
            {
                if (fopen("${globalConfig.executableLocation}/gbuilder-${input[0]}.kexe", "r") != null)
                    system("${globalConfig.executableLocation}/gbuilder-${input[0]}.kexe ${input.drop(1).joinToString(separator = " ") { "\"$it\"" }}")
                else
                    println("Command ${input[0]} not found.")
            }
        }
//        when (input[0])
//        {
//            "create" -> Create(input.drop(1))
//            "quit", "exit" -> exitProcess(0)
//        }
    }

    operator fun invoke()
    {
        println("Hello ${user.username}!")
        println("Here's Geno's GBuilder Git Server.")
        while (true)
        {
            print("GitShell > ")
            try
            {
                val input = readln().split(" ")
                parse(input)
            }
            catch (e: RuntimeException)
            {
                println(e)
                println("Good bye!")
                exitProcess(0)
            }
        }
    }
}

fun main(args: Array<String>)
{
    user = UserConfig(globalConfig, args.getOrElse(0) { "anonymous" } )
    ShellMain()
}
