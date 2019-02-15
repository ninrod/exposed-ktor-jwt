package org.ninrod.blog.endpoint.root

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.root() {
    route("/") {
        get {
            call.respondText("Hello World!", ContentType.Text.Plain)
        }
    }
}
