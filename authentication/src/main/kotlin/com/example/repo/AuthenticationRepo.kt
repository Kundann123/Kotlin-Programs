package com.example.repo

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.audience
import com.example.issuer
import com.example.secret
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Date

class AuthenticationRepo {
    fun tokenGeneration(username: String, password: String):Any {
        if (username == "Admin" && password == "2425") {
            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", username)
                .withExpiresAt(Date(System.currentTimeMillis() + 24 * 60 * 60000))
                .sign(Algorithm.HMAC256(secret))
            return(
                Json.encodeToString(hashMapOf("token" to token))
            )
        } else {
            return (
                Json.encodeToString(hashMapOf("error" to "Wrong login or password"))
            )
        }
    }
}