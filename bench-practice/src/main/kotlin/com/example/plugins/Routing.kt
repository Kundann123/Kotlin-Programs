package com.example.plugins

import com.example.routes.configureInsertData
import com.example.routes.configureLogin
import io.ktor.server.application.Application

fun Application.configureRouting() {
    configureInsertData()
    configureLogin()
}
