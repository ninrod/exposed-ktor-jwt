package org.ninrod.blog

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.ninrod.blog.endpoint.module
import org.ninrod.blog.infra.configureDatabase

fun main() {
    configureDatabase()
    embeddedServer(Netty, port = 8080) { module() }.start(wait = true)
}
