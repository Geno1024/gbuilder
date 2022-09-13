package com.geno1024.builder

import com.geno1024.builder.commands.SystemInit
import kotlin.system.exitProcess

object Main
{
    fun parser(commands: List<String>)
    {
        when (commands[0])
        {
            "exit", "quit", "return" -> exitProcess(0)
            "systeminit" -> SystemInit(commands.drop(1))
        }
    }

    fun repl()
    {
        while (true)
        {
            print("GBuilder > ")
            val commands = readln().split(' ')
            parser(commands)
        }
    }

    fun main(args: Array<String>)
    {
        repl()
    }
}

fun main(args: Array<String>) = Main.main(args)
