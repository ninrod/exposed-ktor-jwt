package org.ninrod.blog.endpoint

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authenticate
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.Routing
import org.ninrod.blog.endpoint.login.login
import org.ninrod.blog.endpoint.root.root
import org.ninrod.blog.endpoint.secret.secret
import org.ninrod.blog.infra.customConfigure

fun Application.module() {
    install(CallLogging)
    install(ContentNegotiation) { gson { setPrettyPrinting() } }
    install(Authentication) { jwt { customConfigure() } }
    install(Routing) {
        login()
        root()
        authenticate {
            secret()
        }
    }
}

