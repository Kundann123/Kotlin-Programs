package com.example.databaseconfig

import io.ktor.server.application.*

object Configuration {
    lateinit var env: ConfigParameters

    fun initConfig(environment: ApplicationEnvironment) {
        env = ConfigParameters(
            databaseUrl = environment.config.property("ktor.db.url").getString(),
            databaseUsername = environment.config.property("ktor.db.username").getString(),
            databasePassword = environment.config.property("ktor.db.password").getString(),
            driverClassName = environment.config.property("ktor.db.driverClassName").getString()
        )
    }
}