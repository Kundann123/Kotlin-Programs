package com.example.service

import com.example.dto.GetData
import com.example.dto.InputDTO

interface InputService {
    suspend fun insertData(body:InputDTO):Any

    suspend fun getData(body: GetData):Any
}