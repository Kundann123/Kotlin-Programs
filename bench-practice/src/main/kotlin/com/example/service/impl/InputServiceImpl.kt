package com.example.service.impl

import com.example.di.kodein
import com.example.dto.GetData
import com.example.dto.InputDTO
import com.example.repo.InputRepo
import com.example.service.InputService
import org.kodein.di.instance

class InputServiceImpl :InputService{
    private val inputRepo by kodein.instance<InputRepo>()

    override suspend fun insertData(body:InputDTO) {
        return inputRepo.insertData(body)
    }

    override suspend fun getData(body: GetData): Any {
        return inputRepo.getData(body)
    }

}