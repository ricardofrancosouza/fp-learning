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

    private data class User(val firstName: String?)
    fun <T, K, V> mapToUser(id: K, users: Map<K, V>, userMapper: (V) -> T): T? {
        val userValue = users[id]
        return userValue?.let { userMapper(it) }
    }
}