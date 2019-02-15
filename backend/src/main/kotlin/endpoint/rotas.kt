package org.ninrod.blog.endpoint

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import org.ninrod.blog.JwtConfig
import org.ninrod.blog.Token
import org.ninrod.blog.user.authGetUsers
import org.ninrod.blog.user.findUserByCredentials
import org.ninrod.blog.user.getUsers

fun Application.rotas() {
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
            findUserByCredentials(call.receive<UserPasswordCredential>())?.let {
                u -> call.respond(Token(JwtConfig.makeToken(u)))
            } ?: call.respond(HttpStatusCode.Unauthorized)
        }

        authenticate {
            get("/secret") {
                call.authentication.principal<JWTPrincipal>()?.let { t ->
                    println("printando o principal: $t")
                    call.respond(authGetUsers(t))
                } ?: call.respond(HttpStatusCode.Unauthorized)
            }
        }
    }
}

