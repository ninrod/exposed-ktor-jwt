package org.ninrod.blog

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import io.ktor.auth.jwt.JWTAuthenticationProvider
import io.ktor.auth.jwt.JWTPrincipal
import org.ninrod.blog.user.User
import org.ninrod.blog.user.getUsers
import java.util.*

data class Token (val token: String)

object JwtConfig {
    const val issuer = "ninrod.org"
    private const val secret = "zAP5MBA4B4Ijz0MZaS48"
    private const val validityInMs = 36_000_00 * 10 // 10 hours
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(issuer).build()

    fun makeToken(user: User): String = JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("id", user.id.value)
            .withClaim("login", user.login)
            .withClaim("description", user.description)
            .withArrayClaim("names", arrayOf<String>(user.firstname, user.lastname))
            .withExpiresAt(getExpiration())
            .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

}

fun JWTAuthenticationProvider.customConfigure() {
    verifier(JwtConfig.verifier)
    realm = JwtConfig.issuer
    validate {
        if (getUsers().filter { u -> u.login == it.payload.getClaim("login").asString() }.any())
            JWTPrincipal(it.payload)
        else
            null
    }
}