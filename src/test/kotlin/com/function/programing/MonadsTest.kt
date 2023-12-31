package com.function.programing

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

    @Test
    fun `should apply fold to the boolean list`() {
        val monoid = AndMonoid()
        val lst = listOf(false, true, true, true)
        val result = lst.fold(monoid.empty()) { acc, i -> monoid.combine(acc, i)}
        assertEquals(false, result)
    }

    @Test
    fun `should get factorial when apply fold to the integer list`() {
        val result = Factorial.factorial(5)
        assertEquals(120, result)
    }

    @Test
    fun `should combine monoids`() {
        val lst = listOf(1, 2, 3, 4, 5)
        val monoidInt = IntMonoid()
        val resultMonoidInt = combineMonoid(lst, monoidInt)
        assertEquals(15, resultMonoidInt)
    }

    @Test
    fun `should recover factorial when using monoid combination`() {
        val result = Factorial.factorial2(5)
        assertEquals(120, result)
    }
    @Test
    fun `should return true when sending a number 5`() {
        assertTrue { isPrime(5) }
    }

    @Test
    fun `should return false when sending a number 6`() {
        assertFalse { isPrime(6) }
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

    class AndMonoid : Monoid<Boolean> {
        override fun combine(x: Boolean, y: Boolean): Boolean = x && y

        override fun empty(): Boolean = true

    }

    class ProductMonoid : Monoid<Double> {
        override fun combine(x: Double, y: Double): Double = x * y

        override fun empty(): Double = 1.0

    }

    class IntProductMonoid: Monoid<Int> {
        override fun combine(x: Int, y: Int): Int = x * y

        override fun empty(): Int = 1
    }

    object Factorial {
        val monoid = IntProductMonoid()
        fun factorial(n: Int): Int = (1..n).toList().fold(monoid.empty()) {acc, i -> monoid.combine(acc, i)}
        private fun <A> combineMonoid(lst: List<A>, m: Monoid<A>): A = lst.fold(m.empty()) {acc, i -> m.combine(acc, i)}
        fun factorial2(n: Int): Int = combineMonoid((1..n).toList(), monoid)
    }

    fun <A> combineMonoid(lst: List<A>, m: Monoid<A>): A = lst.fold(m.empty()) {acc, i -> m.combine(acc, i)}
    fun isPrime(n: Int): Boolean = (2 until n).all { n % it != 0 }
}