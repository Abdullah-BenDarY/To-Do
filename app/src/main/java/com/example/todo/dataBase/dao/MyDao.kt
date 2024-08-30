package com.example.todo.dataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todo.dataBase.moel.ModelTask

@Dao // annotate the interface with @Dao to tell Room that it is a data access object
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createTask(modelTask : ModelTask)

    @Delete
    fun deleteTask(modelTask : ModelTask)

    @Update
    fun updateTask(modelTask : ModelTask)

    @Query("select * from ModelTask")
    fun getAllTask() : List<ModelTask>

    @Query("select * from ModelTask where date = :date")
    fun getTaskByDate(date : Long) : List<ModelTask>

}