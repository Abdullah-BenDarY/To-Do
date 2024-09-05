package com.example.todo.dataBase.moel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity //Table name
data class ModelTask(

    @ColumnInfo(index = true) // to search by index id to search faster
    @PrimaryKey(autoGenerate = true) //by default false
    val id : Int = 0,
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
): Parcelable
