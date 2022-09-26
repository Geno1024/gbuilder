package com.geno1024.gbuilder.utils

import kotlin.test.Test
import kotlin.test.assertNotEquals

object TimeUtilsTest
{
    @Test
    fun getCurrentTimeTest()
    {
        val currentTime = TimeUtils.getCurrentTime()
        println("Now: $currentTime")
        assertNotEquals(-1, currentTime)
    }
}
