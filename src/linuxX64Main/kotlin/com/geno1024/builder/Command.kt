package com.geno1024.builder

interface Command
{
    operator fun invoke(parameters: List<String>)

    fun help()
}
