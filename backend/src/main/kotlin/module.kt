package org.ninrod.blog

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.module() {
    install(ContentNegotiation) { gson { setPrettyPrinting() } }
    routing {
        get("/") { call.respondText("Hello World!", ContentType.Text.Plain) }
        get("/database") { call.respond(getUsers()) }
    }
}

