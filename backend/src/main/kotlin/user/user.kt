package org.ninrod.blog.user

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongIdTable

data class UserDTO (
        val id: Long,
        val login: String,
        val firstname: String,
        val lastname: String,
        val description: String
)

class User(id: EntityID<Long>) : Entity<Long>(id) {
    companion object : EntityClass<Long, User>(UserRepo)

    val login by UserRepo.login
    val firstname by UserRepo.firstname
    val lastname by UserRepo.lastname
    val description by UserRepo.description
    val password by UserRepo.password

    fun toDTO() = UserDTO(id.value, login, firstname, lastname, description ?: "")

    override fun toString(): String = with(StringBuilder()) {
        append("User(")
        append("id=${id.value}, ")
        append("login=$login, ")
        append("firstname=$firstname, ")
        append("lastname=$lastname, ")
        append("description=$description, ")
        append("password=$password")
        append(")")
    }.toString()
}


object UserRepo : LongIdTable("usuario") {
    val login = varchar("login", 10)
    val firstname = varchar("firstname", length = 255)
    val lastname = varchar("lastname", length = 255)
    val description = varchar("description", length = 255).nullable()
    val password = varchar("password", length = 30)
}
