package org.ninrod.blog

import org.jetbrains.exposed.sql.Table

object Usuario: Table() {
    val login = varchar("login", 10).primaryKey()
    val firstname = varchar("firstname", length = 255)
    val lastname = varchar("lastname", length = 255)
    val description = varchar("description", length = 255).nullable()
}

data class User(
        val login: String,
        val firstname: String,
        val lastname: String,
        val description: String
)

