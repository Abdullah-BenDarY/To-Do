package com.example.todo.ui

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentSettingsBinding
import com.example.todo.util.ARABIC_CODE
import com.example.todo.util.DARK
import com.example.todo.util.ENGLISH_CODE
import com.example.todo.util.LIGHT
import com.example.todo.util.SETTING_SP_NAME
import com.example.todo.util.applyLanduageCahnges
import com.example.todo.util.applyModeCahnges
import com.example.todo.util.getCurrentDeviceLanguage

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private lateinit var settingSp: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingSp = requireContext().getSharedPreferences(SETTING_SP_NAME, Context.MODE_PRIVATE)

    }

    override fun onStart() {
        super.onStart()
        setInitialLanguage()
        setInitialModeState()
        initLanguageTvAdapter()
        initModeTvAdapter()
    }

    override fun onClicks() {
        onLanguageTvClick()
        onModeTvClick()
    }

    private fun onModeTvClick() {
        binding.autoCompleteTVModes.setOnItemClickListener { adapterView, view, position, id ->
            val selectedMode = adapterView.getItemAtPosition(position).toString()
            binding.autoCompleteTVModes.setText(selectedMode)
            val isDark = (selectedMode == getString(R.string.dark))
            applyModeCahnges(isDark)
            val modeEditor = settingSp.edit()
            modeEditor.apply{
                putBoolean(DARK , isDark)
                apply()
            }
        }
    }

    private fun onLanguageTvClick() {
        binding.autoCompleteTVLanguages.setOnItemClickListener { adapterView, view, position, id ->
            val selectedLanguage = adapterView.getItemAtPosition(position).toString()
            binding.autoCompleteTVLanguages.setText(selectedLanguage)
            val languageCode = if (selectedLanguage == getString(R.string.english)) ENGLISH_CODE
            else ARABIC_CODE
            applyLanduageCahnges(requireActivity(), languageCode)


        }
    }

    // set defuault language text
    private fun setInitialLanguage() {
        val currentLocalCode =
            AppCompatDelegate.getApplicationLocales()[0]?.language ?: getCurrentDeviceLanguage(
                requireContext()
            )

        val language =
            if (currentLocalCode == ENGLISH_CODE) getString(R.string.english) else getString(R.string.arabic)

        binding.autoCompleteTVLanguages.setText(language)
    }

    private fun setInitialModeState() {
        val currentMode = resources.configuration.uiMode and UI_MODE_NIGHT_MASK
        if (currentMode == UI_MODE_NIGHT_NO) {
            binding.autoCompleteTVModes.setText(getString(R.string.light))
            changeModeIcon(LIGHT)
        } else {
            binding.autoCompleteTVModes.setText(getString(R.string.dark))
            changeModeIcon(DARK)
        }
        binding.modeTil.refreshStartIconDrawableState()
    }

    private fun changeModeIcon(mode: String) {
        if (mode == LIGHT) {
            binding.modeTil.setStartIconDrawable(R.drawable.ic_light_mode)
        } else {
            binding.modeTil.setStartIconDrawable(R.drawable.ic_dark)
        }
    }

    private fun initModeTvAdapter() {
        val modes = resources.getStringArray(R.array.modes).toList()
        val modesAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, modes)
        binding.autoCompleteTVModes.setAdapter(modesAdapter)
    }

    private fun initLanguageTvAdapter() {
        val languages = resources.getStringArray(R.array.languages).toList()
        val languageAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, languages)
        binding.autoCompleteTVLanguages.setAdapter(languageAdapter)

    }
}