package com.example.repo

import com.example.databaseconfig.DBConfig
import com.example.dto.User
import com.example.entities.UserDetails
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.dsl.map
import org.ktorm.dsl.where
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert

class AuthenticationRepo {
    private val databaseConnection = DBConfig.getDatabase()

    suspend fun getAllUsers():Any {
        val result =  DBConfig.dbQuery {
            databaseConnection.from(UserDetails).select().map { UserDetails.createEntity(it) }.map {
                User(
                    it.id,
                    it.username,
                    it.password,
                    it.salt
                )
            }
        }
        return result
    }

    suspend fun getUserByUsername(username: String): List<User> {
        val result = DBConfig.dbQuery {
            databaseConnection.from(UserDetails).select().where {UserDetails.userName eq username}
                .map { UserDetails.createEntity(it) }.map {
                User(
                    it.id,
                    it.username,
                    it.password,
                    it.salt
                )
            }
        }
        return result
    }

    suspend fun saveUser(username: String, password: String, salt: String){
        return DBConfig.dbQuery {
            databaseConnection.insert(UserDetails){
                set(it.userName,username)
                set(it.passWord,password)
                set(it.salt,salt)
            }
        }
    }
}
