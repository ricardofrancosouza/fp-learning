package com.function.programing

import fj.data.Either
import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class HigherOrderFunctionExampleEither {

    @Test
    fun `should return list one element with error and two elements corrects`(){
        val users = getUsers()
        val emailsFound = users.filter { either ->  either.isRight }.map {
            it.right().map { it.email }
        }

        val emailsNotFound = users.filter { either ->  either.isLeft }.map {
            it.left().map { it.text }
        }

        assertTrue {
            emailsFound.size == 2
            emailsNotFound.size == 1
            emailsFound.contains( Either.right("jack@example.com"))
            emailsFound.contains( Either.right("andrea@example.com"))
            emailsNotFound.first().isLeft
        }
        assertEquals("user not found", emailsNotFound.first().left().first())
    }

    data class User(val id: Int, val email: String)
    data class Error(val id: Int, val text: String)

    fun getUsers(): List<Either<Error, User>> =
        listOf(
            Either.right(User(1, "jack@example.com")),
            Either.left(Error(4, "user not found")),
            Either.right(User(2, "andrea@example.com")),
        )

}