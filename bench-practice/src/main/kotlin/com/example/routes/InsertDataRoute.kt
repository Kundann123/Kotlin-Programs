package com.example.routes

import com.example.di.kodein
import com.example.dto.GetData
import com.example.dto.InputDTO
import com.example.service.InputService
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.post
import org.kodein.di.instance

fun Application.configureInsertData(){
    val inputService by kodein.instance<InputService>()
    routing {
        route("/common"){
            post("/insert") {
                val body=call.receive<InputDTO>()
                call.respond(inputService.insertData(body))
            }

            post("/get") {
                val body = call.receive<GetData>()
                call.respond(inputService.getData(body))
            }
        }
    }
}
