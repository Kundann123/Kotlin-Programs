package com.example.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


interface UserDetail : Entity<UserDetail> {
    val id: Int
    val username: String
    val password: String
    val salt: String
}

object UserDetails : Table<UserDetail>("jwt_authentication") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val userName = varchar("username").bindTo { it.username }
    val passWord = varchar("password").bindTo { it.password }
    val salt = varchar("salt").bindTo { it.salt }
}