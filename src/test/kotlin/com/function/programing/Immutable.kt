package com.function.programing

import org.junit.jupiter.api.Test
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
    @Test
    fun `GIVEN return fibonacci WHEN input 8`() {
        val x = fibonacci(8)
        assertEquals(21, x)
    }

    private fun fib(n: Int, a: Int, b: Int): Int {
       return when(n){
           0 ->  a
           1 ->  b
           else ->  fib(n -1, b, a + b)
       }
    }
    private fun fibonacci(n: Int) = fib(n, 0, 1)

}