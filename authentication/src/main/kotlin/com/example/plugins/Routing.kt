package com.example.plugins

import com.example.route.configureAuthenticateRoute
import io.ktor.server.application.Application

fun Application.configureRouting() {
    configureAuthenticateRoute()
}
