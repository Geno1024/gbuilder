package com.geno1024.gbuilder.ds.common

class DateTime(val millisSinceEpoch: Long)
{
    override fun toString(): String = millisSinceEpoch.toString()
}
