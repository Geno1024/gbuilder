package com.geno1024.gbuilder.gitshell

import com.geno1024.gbuilder.gitshell.subcommands.Help
import com.geno1024.gbuilder.gitshell.subcommands.Init
import kotlin.system.exitProcess

object GitShell
{
    fun commandParser(input: List<String>)
    {
        when (input[0])
        {
            "exit", "quit" -> exitProcess(0)
            "help" -> Help(input.drop(1))
            "init" -> Init(input.drop(1))
//            "whoami" ->
        }
    }

    fun main()
    {
        while (true)
        {
            print("GitShell > ")
            try
            {
                val input = readln().split(" ")
                commandParser(input)
            }
            catch (e: Exception)
            {
                exitProcess(0)
            }
        }
    }
}

fun main(args: Array<String>) = if (args.isEmpty())
    GitShell.main()
else
    GitShell.commandParser(args.toList().apply(::println))
