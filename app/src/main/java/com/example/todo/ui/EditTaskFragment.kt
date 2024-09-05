package com.example.todo.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.base.BaseFragment
import com.example.todo.dataBase.MyDataBase
import com.example.todo.dataBase.moel.ModelTask
import com.example.todo.databinding.FragmentEditTaskBinding
import com.example.todo.util.clearDate
import com.example.todo.util.clearTime
import com.example.todo.util.formatDateOnly
import com.example.todo.util.formatTimeOnly
import com.example.todo.util.hideBottomAppBarViews
import com.example.todo.util.setDate
import com.example.todo.util.showBottomAppBarViews
import com.example.todo.util.showDateDialog
import com.example.todo.util.showTimePicker
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class EditTaskFragment : BaseFragment<FragmentEditTaskBinding>(FragmentEditTaskBinding::inflate) {

    private val args by navArgs<EditTaskFragmentArgs>()
    private val date = Calendar.getInstance().apply {
        clearTime()
    }
    private val time = Calendar.getInstance().apply {
        clearDate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomAppBarViews()
        val task = args.task
        setTaskDetails(task)
        onClicks(task)
    }

    override fun onClicks(task: ModelTask) {
        setupTextWatchers()
        binding.apply {
            selectDateTv.setOnClickListener {
                showDateDialog(date) { year, month, day ->
                    updateDateTv(year, month, day)
                    task.date = date.timeInMillis
                }
            }
            selectTimeTv.setOnClickListener {
                showTimePicker(time) {
                    updateTimeTv(time)
                    task.time = time.timeInMillis
                }
            }

            btnSave.setOnClickListener {
                if (!validate()) return@setOnClickListener
                    updateTask(args.task)
                    findNavController().navigateUp()


            }
        }
    }

    private fun setTaskDetails(task: ModelTask) {
        binding.title.setText(task.title)
        binding.description.setText(task.description)
        binding.selectDateTv.text = task.date?.formatDateOnly()
        binding.selectTimeTv.text = task.date?.formatTimeOnly()

    }

    private fun updateTask(task: ModelTask) {
        val title = binding.title.text.toString()
        val description = binding.description.text.toString()
        task.title = title
        task.description = description
        MyDataBase.dp?.myDao()?.updateTask(task)
    }

    private fun updateDateTv(year: Int, month: Int, day: Int) {
        "$day / ${month + 1} / $year".also {
            binding.selectDateTv.text = it
        }
    }

    private fun updateTimeTv(selectedDate: Calendar) {
        val timeFormat =
            SimpleDateFormat("hh:mm a", Locale.getDefault()) // a --> 12-hour format with AM/PM
        binding.selectTimeTv.text = timeFormat.format(selectedDate.time)
    }

    private fun validate(): Boolean {
        var isValid = true

        val title = binding.titleTil.editText?.text.toString()
        if (title.isBlank()) {
            binding.titleTil.error = "Please enter title"
            isValid = false
        }
        return isValid
    }

    private fun setupTextWatchers() {
        binding.titleTil.editText?.addTextChangedListener {
            binding.titleTil.error = null
        }
    }
}
