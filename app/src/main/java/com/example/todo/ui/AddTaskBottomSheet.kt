package com.example.todo.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.todo.R
import com.example.todo.base.BaseBottomSheet
import com.example.todo.dataBase.MyDataBase
import com.example.todo.dataBase.moel.ModelTask
import com.example.todo.databinding.BottomSheetAddTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale


class AddTaskBottomSheet :
    BaseBottomSheet<BottomSheetAddTaskBinding>(BottomSheetAddTaskBinding::inflate) {
    private val selectedDate = Calendar.getInstance()
    private val dataBase = MyDataBase
    private val newTask = ModelTask()

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
                initTimeDialog(selectedDate)
            }

            addTaskBtn.setOnClickListener {
                if (!validate()) return@setOnClickListener
                val title = binding.titleTil.editText?.text.toString()
                val description = binding.descriptionTil.editText?.text.toString()
                selectedDate.timeInMillis
                createTask(newTask , title , description ,
                    selectedDate.timeInMillis , selectedDate.timeInMillis)
                dismiss
            }

        }
    }


    private fun createTask(newTask: ModelTask , title: String,
                           description: String , date : Long , time : Long){
        newTask.apply {
            this.title = title
            this.description = description
            this.date = date
            this.time = time
            this.isDone = false
        }
        dataBase.dp?.myDao()?.createTask(newTask)
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

    private fun initTimeDialog(selectedDate: Calendar) {
        val dialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedDate.set(Calendar.MINUTE, minute)
                updateTimeTv(selectedDate)
            },
            selectedDate.get(Calendar.HOUR_OF_DAY),
            selectedDate.get(Calendar.MINUTE),
            false // Set to false to use the 12-hour format with AM/PM
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

    private fun updateTimeTv(selectedDate: Calendar) {
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault()) // a --> 12-hour format with AM/PM
        binding.selectTimeTv.text = timeFormat.format(selectedDate.time)
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