package org.ninrod.blog

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    val db: String = System.getenv("DATABASE_ADDRESS") ?: "localhost"
    Database.connect(
            "jdbc:postgresql://$db:5432/",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "example"
    )
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        routing {
            get("/") {
                call.respondText("Hello World!", ContentType.Text.Plain)
            }
            get("/database") {
                call.respond(getUsers())
            }
        }
    }.start(wait = true)
}

fun getUsers(): List<User> {
    val users = arrayListOf<User>()
    transaction {
        addLogger(StdOutSqlLogger)
        for (user in Usuario.selectAll()) {
            users.add(User(
                    login = user[Usuario.login],
                    firstname = user[Usuario.firstname],
                    lastname = user[Usuario.lastname],
                    description = user[Usuario.description]?: "no description"
            ))
            println(users)
        }
    }
    return users
}

