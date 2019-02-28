package org.ninrod.blog.endpoint.secret

import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import org.ninrod.blog.user.authGetUsers

fun Route.secret() {
    get("/secret") {
        call.authentication.principal<JWTPrincipal>()
                ?.let { princ -> call.respond(authGetUsers(princ)) }
                ?: call.respond(HttpStatusCode.Unauthorized)
    }
}
