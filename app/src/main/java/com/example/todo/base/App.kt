package com.example.todo.base

import android.app.Application
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import com.example.todo.dataBase.MyDataBase
import com.example.todo.util.DARK
import com.example.todo.util.SETTING_SP_NAME
import com.example.todo.util.applyModeCahnges

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MyDataBase.crateDataBase(this)
        setNightMode()
    }

    // get saved Night mode auto
    private fun setNightMode() {
        val isDark = getSharedPreferences(SETTING_SP_NAME , MODE_PRIVATE).getBoolean(DARK, getDeviceModeState())
        applyModeCahnges(isDark)
    }
    private fun getDeviceModeState() : Boolean{
        val currentMode = resources.configuration.uiMode and UI_MODE_NIGHT_MASK
        return currentMode == UI_MODE_NIGHT_YES
    }
}