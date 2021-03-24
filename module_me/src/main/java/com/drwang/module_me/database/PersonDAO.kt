package com.drwang.module_me.database

import androidx.room.*

@Dao
interface PersonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: Person): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert2Person(vararg person: Person):Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersons(personList: ArrayList<Person>): List<Long>

    @Update()
    fun updatePerson(person: Person): Int

    @Delete
    fun deletePerson(person: Person): Int

    @Query("select * from persons")
    fun queryPerson(): List<Person>

    @Query("select * from persons where personAge > :age")
    fun queryPersonByAge(age:Int):List<Person>
}