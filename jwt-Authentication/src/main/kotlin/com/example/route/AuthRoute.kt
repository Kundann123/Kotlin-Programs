package com.example.route

import com.example.dto.AuthRequest
import com.example.dto.AuthResponse
import com.example.repo.AuthenticationRepo
import com.example.security.hashing.SHA256HashingService
import com.example.security.hashing.SaltedHash
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.post
import io.ktor.server.routing.get

fun Application.configureAuthentication(
    tokenService: JwtTokenService, hashingService: SHA256HashingService, tokenConfig: TokenConfig
) {
    routing {
        route("/page") {
            post("/signup") {
                val authenticationRepo = AuthenticationRepo()
                val body = call.receive<AuthRequest>()

                val areFieldsBlank = body.username.isBlank() || body.password.isBlank()
                val isPasswordTooShort = body.password.length < 8
                if (areFieldsBlank || isPasswordTooShort) {
                    call.respond(HttpStatusCode.Conflict, "Check the Fields")
                    return@post
                }

                val existingUser = authenticationRepo.getUserByUsername(body.username)
                if (existingUser.isNotEmpty()) {
                    call.respond(HttpStatusCode.Conflict, "User Exists")
                    return@post
                }

                val saltedHash = hashingService.generateSaltedHash(body.password)
                val user = authenticationRepo.saveUser(body.username, saltedHash.hash, saltedHash.salt)
                if (user == null) {
                    call.respond(HttpStatusCode.Conflict, "Server Error")
                    return@post
                }
                call.respond(HttpStatusCode.OK, "User Created")
            }

            post("/signin") {
                val authenticationRepo = AuthenticationRepo()
                val body = call.receive<AuthRequest>()

                val user = authenticationRepo.getUserByUsername(body.username)
                if (user == null) {
                    call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
                    return@post
                }

                val isValidPassword = hashingService.verify(
                    value = body.password,
                    saltedHash = SaltedHash(
                        hash = user[0].password,
                        salt = user[0].salt
                    )
                )

                if (!isValidPassword) {
                    call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
                    return@post
                }

                val token = tokenService.generateToken(
                    config = tokenConfig,
                    TokenClaim(
                        name = "userId",
                        value = user[0].id.toString()
                    )
                )
                call.respond(HttpStatusCode.OK, AuthResponse(token))
            }

            authenticate {
                get("/authenticate") {
                    call.respond(HttpStatusCode.OK)
                }
            }

//            authenticate {
//                get("/secret") {
//                    val principal = call.principal<JWTPrincipal>()
//                    val userId = principal?.getClaim("userId", String::class)
//                    call.respond(HttpStatusCode.OK, "Your userId is $userId")
//                }
//            }

            get("/users") {
                val authenticationRepo = AuthenticationRepo()
                val users = authenticationRepo.getAllUsers()
                call.respond(HttpStatusCode.OK, users)
            }
        }
    }
}