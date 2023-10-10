package com.example.plugins

import com.example.route.configureAuthentication
import com.example.security.hashing.SHA256HashingService
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenConfig
import io.ktor.server.application.*

fun Application.configureRouting(
    tokenService: JwtTokenService,
    hashingService: SHA256HashingService,
    tokenConfig: TokenConfig
) {
    configureAuthentication(
        tokenService,
        hashingService,
        tokenConfig
    )
}
