package com.example.plugins

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    install(ContentNegotiation){
        json()
    }
    routing {
        get("/") {
            val htmlContent = File("src/main/resources/files/login.html").readText()
            call.respondText(htmlContent, contentType = ContentType.Text.Html)
        }

        post("/login") {
            val params: Parameters = call.receiveParameters()
            val username = params["username"]
            val password = params["password"]

            val user="123"
            val pass="123"
            if (user==username && pass==password){
                call.respondText("Login Successful")
            }
            else{
                call.respond("Invalid Credentials")
            }

        }
    }
}



