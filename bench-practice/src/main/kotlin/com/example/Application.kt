package com.example

import com.example.config.Configuration
import com.example.config.DBConfig
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    Configuration.initConfig(this.environment)
    configureSerialization()
    configureRouting()
    DBConfig.getDatabase()
}
