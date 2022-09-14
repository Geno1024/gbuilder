package com.geno1024.builder

interface Command
{
    fun help()

    operator fun invoke(parameters: List<String>)
}
