package com.example

import com.example.plugins.configureRouting
import com.example.plugins.configureAuthentication
import io.ktor.server.application.Application

const val secret = "secret"
const val issuer = "http://0.0.0.0:8080/"
const val audience = "http://0.0.0.0:8080/page"
const val myRealm = "Access to 'page'"

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("UNUSED")
fun Application.module() {
    configureAuthentication()
    configureRouting()
}
