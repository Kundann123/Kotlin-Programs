package com.example.routes

import com.example.di.kodein
import com.example.dto.LoginDTO
import com.example.service.LoginService
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.post
import org.kodein.di.instance

fun Application.configureLogin(){
    val loginService by kodein.instance<LoginService>()
    routing {
        route("/login"){
            post("/signin") {
                val body=call.receive<LoginDTO>()
                call.respond(loginService.saveLoginData(body))
            }

            post("/signup") {
                val body=call.receive<LoginDTO>()
                call.respond(loginService.signUp(body))
            }
        }
    }
}