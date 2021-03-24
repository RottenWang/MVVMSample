package com.drwang.module_me.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Person::class), version = 1, exportSchema = false)
abstract  class PersonDataBase :RoomDatabase(){
    abstract fun  personDAO():PersonDAO
}