package org.ninrod.blog.user

import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.jwt.JWTPrincipal
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Long.par() = this % 2 == 0L
fun authGetUsers(token: JWTPrincipal): List<UserDTO> = getUsers().filter {
    if (token.payload.getClaim("id").asLong().par()) it.id.value.par() else !it.id.value.par()
}.map { it.toDTO() }

fun getUsers(): List<User> = transaction {
    addLogger(StdOutSqlLogger)
    User.all().toList()
}

fun findUserByCredentials(credential: UserPasswordCredential): User? = transaction {
    println(credential)
    getUsers().filter {
        println(it)
        it.password == credential.password && it.login == credential.name
    }.firstOrNull()
}
