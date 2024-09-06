package com.example.todo.util

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.FragmentActivity


fun getCurrentDeviceLanguage(context: Context): String{
    return context.resources.configuration.locales[0].language

}

fun applyModeCahnges(isDark: Boolean) {
    if (isDark)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    else
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}

fun applyLanduageCahnges( fragmentActivity : FragmentActivity , languageCode: String) {
    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
    fragmentActivity.let {
        it.finish()
        it.startActivity(it.intent)
    }

}