package com.function.programing

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
 *Monad functor tests
 * Note: every monad is also a functor, so it must have a map function
 */
class MonadsTest {

    @Test
    fun `GIVEN list of ids WHEN execute flapMap on usersMap SHOULD return a list of user`(){
        val listToValidate = listOf(
            User(firstName = "Arrascaeta"),
            User("Everton Riveiro"),
            User("Adriano Imperador")
        )
        val userIds = listOf(1,2,3)
        val getUser: (Int, Map<Int, String>) -> User = {
                id: Int, users: Map<Int,String> -> User(firstName = users[id])
        }
        val userMap = mapOf(
            1 to "Arrascaeta",
            2 to "Everton Riveiro",
            3 to "Adriano Imperador"
        )
        val users = userIds.flatMap { id -> listOf(getUser(id, userMap))}

        assertEquals(listToValidate, users)
    }

    @Test
    fun `GIVEN list of ids WHEN execute mapToUser on usersMap SHOULD return a list of user`(){
        val listToValidate = listOf(
            User(firstName = "Arrascaeta"),
            User("Everton Riveiro"),
            User("Adriano Imperador")
        )
        val userMap = mapOf(
            1 to "Arrascaeta",
            2 to "Everton Riveiro",
            3 to "Adriano Imperador"
        )
        val users = mapToUser(2, userMap) { User(it)}

        assertEquals("Everton Riveiro", users!!.firstName)
    }

    @Test
    fun `should return the sum of the range from 1 to 10 when apply fold to a list of integers`(){
        val monoid = IntMonoid()
        val lst = (1..10).toList()
        val result = lst.fold(monoid.empty()) { acc, i -> monoid.combine(acc, i)}
        assertEquals(55, result)
    }

    @Test
    fun `should concatenate strings using fold`() {
        val monoid = StringMonoid()
        val lst = listOf("a", "b", "c", "d")
        val result = lst.fold(monoid.empty()) { acc, i -> monoid.combine(acc, i)}
        assertEquals("abcd", result)
    }

        private data class User(val firstName: String?)
    fun <T, K, V> mapToUser(id: K, users: Map<K, V>, userMapper: (V) -> T): T? {
        val userValue = users[id]
        return userValue?.let { userMapper(it) }
    }

    class IntMonoid() : Monoid<Int> {
        override fun combine(x: Int, y: Int): Int = x + y

        override fun empty(): Int = 0

    }

    interface Monoid<A> {
        fun combine(x: A, y: A): A
        fun empty(): A
    }

    class StringMonoid : Monoid<String> {
        override fun combine(x: String, y: String): String  = x + y

        override fun empty(): String = ""

    }

}