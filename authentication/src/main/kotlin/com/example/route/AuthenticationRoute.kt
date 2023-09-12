package com.example.route

import com.example.repo.AuthenticationRepo
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.post
import io.ktor.server.routing.get

fun Application.configureAuthenticateRoute(){
    routing {
        route("/page"){
            post("/token") {
                val parameters = call.receiveParameters()
                val username = parameters["username"].toString()
                val password = parameters["password"].toString()

                val authenticationRepo=AuthenticationRepo()
                call.respond(authenticationRepo.tokenGeneration(username,password))
            }

            authenticate("myAuth") {
                get("/login") {
                    val principal = call.principal<JWTPrincipal>()
                    val username = principal!!.payload.getClaim("username").asString()
                    val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
                    call.respondText("Hi, $username! Token is expired at $expiresAt ms.")
                }
            }
        }
    }
}