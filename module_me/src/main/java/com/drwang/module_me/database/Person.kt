package com.drwang.module_me.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(


        @PrimaryKey
        var personId: Int? = null,
        @ColumnInfo(name = "person_name")
        var personName: String? = null,
        var personAge: Int? = null,
        @Ignore
        var personLike: String? = null

)
