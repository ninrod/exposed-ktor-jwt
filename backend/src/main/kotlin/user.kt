package org.ninrod.blog

import io.ktor.auth.UserPasswordCredential
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

data class User( val login: String, val firstname: String, val lastname: String, val description: String )

object UserRepo : Table("usuario") {
    val login = varchar("login", 10).primaryKey()
    val firstname = varchar("firstname", length = 255)
    val lastname = varchar("lastname", length = 255)
    val description = varchar("description", length = 255).nullable()
    val password = varchar("password", length = 30)
}

fun getUsers(): List<User> {
    return transaction {
        addLogger(StdOutSqlLogger)
        UserRepo.selectAll().toList().map {
            User(
                    it[UserRepo.login],
                    it[UserRepo.firstname],
                    it[UserRepo.lastname],
                    it[UserRepo.description] ?: "description"
            )
        }
    }
}

fun findUserByCredentials(credential: UserPasswordCredential): User =
    transaction {
        val r = UserRepo.selectAll().toList().filter { it[UserRepo.password] == credential.password }.first()
        User(r[UserRepo.login], r[UserRepo.firstname], r[UserRepo.lastname], r[UserRepo.description] ?: "")
    }
