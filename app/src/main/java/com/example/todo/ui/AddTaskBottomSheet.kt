package com.example.todo.ui

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.todo.R
import com.example.todo.base.BaseBottomSheet
import com.example.todo.databinding.BottomSheetAddTaskBinding


class AddTaskBottomSheet :
    BaseBottomSheet<BottomSheetAddTaskBinding>(BottomSheetAddTaskBinding::inflate) {
    private val selectedDate = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onClicks() {
        setupTextWatchers()
        binding.apply {
            selectDateTv.setOnClickListener {
                initDateDialog(selectedDate)
            }

            selectTimeTv.setOnClickListener {

            }
            addTaskBtn.setOnClickListener {
                if (validate()) dismiss
            }
            title.setOnClickListener {

            }
            description.setOnClickListener {

            }

        }
    }

    private fun initDateDialog(selectedDate: Calendar) {
        val dialog = DatePickerDialog(
            requireContext(),
            { view, year, month, dayOfMonth ->
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateTv()
            }, selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    private fun updateDateTv() {
        val year = selectedDate.get(Calendar.YEAR)
        val month = selectedDate.get(Calendar.MONTH)
        val day = selectedDate.get(Calendar.DAY_OF_MONTH)

        "$day / ${month + 1} / $year".also {
            binding.selectDateTv.text = it
        }
    }

    private fun setupTextWatchers() {
        binding.titleTil.editText?.addTextChangedListener {
            binding.titleTil.error = null
        }

        binding.descriptionTil.editText?.addTextChangedListener {
            binding.descriptionTil.error = null
        }

        binding.selectDateTv.addTextChangedListener {
            binding.selectDateTil.error = null
        }

        binding.selectTimeTv.addTextChangedListener {
            binding.selectTimeTil.error = null
        }
    }

    private fun validate(): Boolean {
        var isValid = true

        val title = binding.titleTil.editText?.text.toString()
        if (title.isBlank()) {
            binding.titleTil.error = "Please enter title"
            isValid = false
        }

        val description = binding.descriptionTil.editText?.text.toString()
        if (description.isBlank()) {
            binding.descriptionTil.error = "Please enter description"
            isValid = false
        }

        val date = binding.selectDateTv.text.toString()
        if (date.isBlank()) {
            binding.selectDateTil.error = "Please enter date"
            isValid = false
        }

        val time = binding.selectTimeTv.text.toString()
        if (time.isBlank()) {
            binding.selectTimeTil.error = "Please enter time"
            isValid = false
        }
        return isValid
    }

}