package com.geno1024.gbuilder.gitshell

import com.geno1024.gbuilder.gitshell.subcommands.Create
import kotlin.system.exitProcess

object GitShell
{
    fun parse(input: List<String>)
    {
        when (input[0])
        {
            "create" -> Create(input.drop(1))
            "quit", "exit" -> exitProcess(0)
        }
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
    user = User(args.getOrElse(0) { "anonymous" } )
    GitShell()
}
