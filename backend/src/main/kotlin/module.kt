package org.ninrod.blog

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.authenticate
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing

fun Application.module() {
    install(CallLogging)
    install(ContentNegotiation) { gson { setPrettyPrinting() } }
    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = JwtConfig.issuer
            validate {
                if (getUsers().filter { u -> u.login == it.payload.getClaim("login").asString() }.any())
                    JWTPrincipal(it.payload)
                else
                    null
            }
        }
    }
    routing {
        route("/") { get { call.respondText("Hello World!", ContentType.Text.Plain) } }

        post("/login") {
            val user = findUserByCredentials(call.receive<UserPasswordCredential>())
            val token = JwtConfig.makeToken(user)
            call.respond(Token(token))
        }

        authenticate {
            get("/secret") { call.respond(getUsers()) }
        }
    }
}

