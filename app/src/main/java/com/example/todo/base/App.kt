package com.example.todo.base

import android.app.Application
import com.example.todo.dataBase.MyDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MyDataBase.crateDataBase(this)
    }
}