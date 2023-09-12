package com.example.di

import com.example.repo.InputRepo
import com.example.repo.LoginRepo
import com.example.service.InputService
import com.example.service.LoginService
import com.example.service.impl.InputServiceImpl
import com.example.service.impl.LoginServiceImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val kodein= DI{
    bind<InputService>() with singleton { InputServiceImpl() }
    bind<InputRepo>() with singleton { InputRepo() }
    bind<LoginService>() with singleton { LoginServiceImpl() }
    bind<LoginRepo>() with singleton { LoginRepo() }
}