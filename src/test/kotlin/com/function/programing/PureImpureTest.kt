package com.function.programing

import kotlin.test.Test

class PureImpureTest {

    @Test
    fun `should print "10"`() {
        val se = MySE(10)
        se.run()
    }
    @Test
    fun `should to exeucte thread`() {
        val x = Thread {
            println("Batman")
        }
        x.start()
        x.join()
    }
    @Test
    fun `should to execute twice thread `() {
        val x = Thread {
            println("Batman")
        }
        x.start()
        x.join()
    }

    private interface SE<A> {
        val a: A
        fun run(): Unit
    }

   private  class MySE(
       override val a: Int
   ) : SE<Int> {
       override fun run() {
          println(a)
       }

   }

}