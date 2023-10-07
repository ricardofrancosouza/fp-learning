package com.function.programing

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FunctorTest {
    /*
    there are three properties a functor from category C to Category D must satisfy:
    1 - A functor F takes objects in C to Objects in D.
    2 - F takes morphisms in C to morphisms in D.
    3 - F(f o g) = F(f) o F(g) where f and g are morphisms
     */


    @Test
    fun `should calculate square`(){
        val square: (Int) -> Int = {x: Int -> x * x }

        val originList = listOf(1,2,3,4)
        assertEquals(listOf(1,4,9, 16), originList.map(square))
    }

    @Test
    fun `should calculate square from length of list`(){
        val square: (Int) -> Int = {
            x: Int -> x * x
        }
        val length: (String) -> Int = {
            it.length
        }
        val originList = listOf("hello", "universe")
        assertEquals(listOf(25, 64), originList.map { square(length(it)) } )

    }

}