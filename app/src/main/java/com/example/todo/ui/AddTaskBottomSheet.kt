package com.example.todo.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.base.BaseBottomSheet
import com.example.todo.dataBase.MyDataBase
import com.example.todo.dataBase.moel.ModelTask
import com.example.todo.databinding.BottomSheetAddTaskBinding
import com.example.todo.util.clearDate
import com.example.todo.util.clearTime
import com.example.todo.util.showDateDialog
import com.example.todo.util.showTimePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTaskBottomSheet() :
    BaseBottomSheet<BottomSheetAddTaskBinding>(BottomSheetAddTaskBinding::inflate) {
    private val dataBase = MyDataBase
    private val newTask = ModelTask()
    private val calendar  = Calendar.getInstance()
    private val date = Calendar.getInstance().apply {
        clearTime()
    }
    private val time = Calendar.getInstance().apply {
        clearDate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateDateTv( year = date.get(Calendar.YEAR) ,
            month =  date.get(Calendar.MONTH) ,
            day= date.get(Calendar.DAY_OF_MONTH))
        updateTimeTv(time)
    }

    override fun onClicks() {
        setupTextWatchers()
        binding.apply {
            selectDateTv.setOnClickListener {
                showDateDialog(date) { year: Int, month: Int, day: Int ->
                    updateDateTv(year, month, day) }
            }
            selectTimeTv.setOnClickListener {
                showTimePicker(time){ updateTimeTv(time) }
            }

            addTaskBtn.setOnClickListener {
                if (!validate()) return@setOnClickListener
                calendar.clearTime()
                createTask(newTask)
                findNavController().navigate(R.id.taskFragment)
                dismiss()
            }
        }
    }

    private fun createTask(newTask: ModelTask){
        val title = binding.titleTil.editText?.text.toString()
        val description = binding.descriptionTil.editText?.text.toString()
        newTask.apply {
            this.title = title
            this.description = description
            this.date = calendar.timeInMillis
            this.time = calendar.timeInMillis
            this.isDone = false
        }
        dataBase.dp?.myDao()?.createTask(newTask)
    }

    private fun updateDateTv(year: Int, month: Int, day: Int) {
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

        val date = binding.selectDateTv.text.toString()
        if (date.isBlank()) {
            binding.selectDateTil.error = "Please enter date"
            isValid = false
        }
        return isValid
    }
}

