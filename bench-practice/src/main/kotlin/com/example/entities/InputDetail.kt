package com.example.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface InputDetail : Entity<InputDetail> {
    val id: Int
    val name: String
    val mob: String
    val sDate: String
    val dDate: String
}

object InputDetails : Table<InputDetail>("bench_table") {
    val id = int("id").bindTo { it.id }.primaryKey()
    val name = varchar("cust_name").bindTo { it.name }
    val mob = varchar("cust_mobile").bindTo { it.mob }
    val sData = varchar("s_date").bindTo { it.sDate }
    val dData = varchar("d_date").bindTo { it.dDate }
}
