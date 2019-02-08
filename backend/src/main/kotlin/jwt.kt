package org.ninrod.blog

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import java.util.*

object JwtConfig {
    const val issuer = "ninrod.org"
    private const val secret = "zAP5MBA4B4Ijz0MZaS48"
    private const val validityInMs = 36_000_00 * 10 // 10 hours
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(issuer).build()

    fun makeToken(user: User): String = JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("login", user.login)
            .withArrayClaim("names", arrayOf<String>(user.firstname, user.lastname))
            .withExpiresAt(getExpiration())
            .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}