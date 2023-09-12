package com.example.repo

import com.example.config.DBConfig
import com.example.dto.GetData
import com.example.dto.InputDTO
import com.example.dto.LoginDTO
import com.example.entities.InputDetails
import org.ktorm.dsl.*

class InputRepo {
    private val databaseConnection = DBConfig.getDatabase()

    suspend fun insertData(body: InputDTO) {
        return DBConfig.dbQuery {
            databaseConnection.insert(InputDetails) {
                set(it.id, body.id)
                set(it.name, body.name)
                set(it.mob, body.mob)
                set(it.sData, body.sDate)
                set(it.dData, body.dDate)
            }
        }
    }

    suspend fun getData(body: GetData): Any {
        val result= DBConfig.dbQuery {
            databaseConnection.from(InputDetails).select().where { body.id eq InputDetails.id }
                .map { InputDetails.createEntity(it) }.map {
                InputDTO(
                    it.id,
                    it.name,
                    it.mob,
                    it.sDate,
                    it.dDate
                )
            }
        }
        return result
    }

}
