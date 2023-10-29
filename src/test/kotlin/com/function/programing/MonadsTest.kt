package com.function.programing

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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

    private data class User(val firstName: String?)

}