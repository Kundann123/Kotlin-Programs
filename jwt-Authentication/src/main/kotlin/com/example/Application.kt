package com.example

import com.example.databaseconfig.Configuration
import com.example.databaseconfig.DBConfig
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import com.example.security.hashing.SHA256HashingService
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenConfiguration
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    TokenConfiguration.initConfig(this.environment)
    val tokenService = JwtTokenService()
    val hashingService = SHA256HashingService()
    val tokenConfig=TokenConfiguration.env
    configureSecurity(tokenConfig)

    Configuration.initConfig(this.environment)
    configureRouting(tokenService,hashingService,tokenConfig)
    DBConfig.getDatabase()
    configureSerialization()
}
