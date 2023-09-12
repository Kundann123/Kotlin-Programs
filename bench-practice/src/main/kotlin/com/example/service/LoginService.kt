package com.example.service

import com.example.dto.LoginDTO

interface LoginService {
    suspend fun saveLoginData(body: LoginDTO): Any

    suspend fun signUp(body: LoginDTO): Any
}