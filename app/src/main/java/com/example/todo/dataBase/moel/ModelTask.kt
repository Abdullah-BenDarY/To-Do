package com.example.todo.dataBase.moel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //Table name
data class ModelTask(
    @PrimaryKey(autoGenerate = true) //by default false
    @ColumnInfo(index = true) // to search by index id to search faster
    val id : Int,
    @ColumnInfo
    var title : String? = null,
    @ColumnInfo
    var description : String? = null,
    @ColumnInfo
    var date : Long? = null,
    @ColumnInfo
    var time : Long? = null,
    @ColumnInfo
    var  isDone : Boolean? = null,
)
