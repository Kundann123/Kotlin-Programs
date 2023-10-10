package com.example.security.token

import io.ktor.server.application.ApplicationEnvironment
import java.time.Duration

object TokenConfiguration {
    lateinit var env : TokenConfig

    fun initConfig(enviroment:ApplicationEnvironment){
        env= TokenConfig(
            issuer = enviroment.config.property("jwt.issuer").getString(),
            audience = enviroment.config.property("jwt.audience").getString(),
            expiresIn = Duration.ofHours(1).toMillis(),
            secret = enviroment.config.property("jwt.secret").getString()
        )
    }

}
