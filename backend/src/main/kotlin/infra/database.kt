package org.ninrod.blog.infra

import org.jetbrains.exposed.sql.Database

fun configureDatabase() = with(Database) {
    val db = System.getenv("DATABASE_ADDRESS") ?: "localhost"
    connect(
            url = "jdbc:postgresql://$db:5432/",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "example"
    )
}
