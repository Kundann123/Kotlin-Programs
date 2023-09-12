package com.example.entities


import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

interface LoginDetail:Entity<LoginDetail>{
    val userName:String
    val passWord:String
}

object LoginDetails: Table<LoginDetail>("login_data"){
    val userName=varchar("username").bindTo { it.userName }.primaryKey()
    val passWord=varchar("password").bindTo { it.passWord }
}