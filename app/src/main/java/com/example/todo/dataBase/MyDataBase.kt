package com.example.todo.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.dataBase.dao.MyDao
import com.example.todo.dataBase.moel.ModelTask

@Database(entities = [ModelTask::class], version = 1, exportSchema = false)
//entities:  list of models that belong to the database
//version:  database version
//exportSchema:  export the schema of the database to a folder
abstract class MyDataBase : RoomDatabase() {

    abstract fun myDao() : MyDao

    companion object{
        //singlton pattern
        private var _dp: MyDataBase? = null
        val dp get() = _dp
        private const val DB_NAME = "myDataBase"

        fun crateDataBase(context: Context): MyDataBase {
            if (_dp == null){
                _dp = Room.databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java , DB_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return _dp!!
        }
    }

}