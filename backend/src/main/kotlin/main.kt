package org.ninrod.blog

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.ninrod.blog.endpoint.module
import org.ninrod.blog.infra.configureDatabase

fun main() {
    configureDatabase()
    embeddedServer(Netty, port = 8080) { module() }.start(wait = true)
}


abstract class BaseEmail(email: String, subject: String, body: String) {
    abstract fun html(): String
    fun send(): String = html()
}

class SignupEmail(
        email: String,
        subject: String,
        body: String
): BaseEmail(email, subject, body) {
    constructor() : this("", "", "")
    override fun html(): String = "oi"
}

