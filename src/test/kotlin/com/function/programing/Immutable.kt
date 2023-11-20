package com.function.programing

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class Immutable {
    @Test
    fun `GIVEN return the sum of a list of integers WHEN string contains numbers 1 4 5`(){
        val textToTest = "item 1 item 4 item 5"
        val sum = textToTest
                     .split(" ")
                     .filter { s -> s.matches("\\d+".toRegex()) }
                     .map { it.toInt() }
                     .sum()

        assertEquals(10, sum)
    }
}