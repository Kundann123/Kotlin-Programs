package com.example.repo

import com.example.config.DBConfig
import com.example.dto.LoginDTO
import com.example.entities.LoginDetails
import io.ktor.http.*
import org.ktorm.dsl.*

class LoginRepo {
    private val databaseConnection = DBConfig.getDatabase()
    suspend fun saveSignUpData(body: LoginDTO) {
        return DBConfig.dbQuery {
            databaseConnection.insert(LoginDetails) {
                set(it.userName, body.userName)
                set(it.passWord, body.passWord)
            }
        }
    }

    suspend fun signIn(body: LoginDTO): Any {
        val result = DBConfig.dbQuery {
            databaseConnection.from(LoginDetails).select()
                .where { (body.userName eq LoginDetails.userName) and (body.passWord eq LoginDetails.passWord) }
                .map { LoginDetails.createEntity(it) }.map {
                    LoginDTO(
                        it.userName,
                        it.passWord
                    )
                }
        }
        return if (result.contains(body)) {
            HttpStatusCode.Found
        } else {
            HttpStatusCode.NotAcceptable
        }
    }
}
