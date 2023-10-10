package com.example.service.impl

import com.example.di.kodein
import com.example.dto.LoginDTO
import com.example.repo.LoginRepo
import com.example.service.LoginService
import org.kodein.di.instance

class LoginServiceImpl : LoginService {
    private val loginRepo by kodein.instance<LoginRepo>()

    override suspend fun saveLoginData(body: LoginDTO) {
        return loginRepo.saveSignUpData(body)
    }

    override suspend fun signIn(body: LoginDTO): Any {
        return loginRepo.signIn(body)
    }

}