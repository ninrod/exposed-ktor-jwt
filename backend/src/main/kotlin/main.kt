package org.ninrod.blog

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import org.ninrod.blog.endpoint.rotas

fun main() {
    db()
    server()
}

fun server() = embeddedServer(Netty, port = 8080) { rotas() }.start(wait = true)
fun db() = with(Database) {
    val db = System.getenv("DATABASE_ADDRESS") ?: "localhost"
    connect(
            url = "jdbc:postgresql://$db:5432/",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "example"
    )
}
