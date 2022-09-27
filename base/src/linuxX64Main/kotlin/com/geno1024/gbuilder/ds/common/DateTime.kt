package com.geno1024.gbuilder.ds.common

import com.geno1024.gbuilder.utils.TimeUtils

class DateTime(val timestamp: Long = TimeUtils.getCurrentTime())
{
    override fun toString(): String = timestamp.toString()
}
