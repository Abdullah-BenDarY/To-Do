package com.example.todo.ui.tasks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.todo.adapters.AdapterTasks
import com.example.todo.base.BaseFragment
import com.example.todo.dataBase.MyDataBase
import com.example.todo.dataBase.moel.ModelTask
import com.example.todo.databinding.FragmentTaskBinding
import com.example.todo.util.clearTime
import com.example.todo.util.setDate
import com.example.todo.util.showBottomAppBarViews
import com.example.todo.util.showDeleteDialog
import com.example.todo.util.showToast
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

class TaskFragment : BaseFragment<FragmentTaskBinding>(FragmentTaskBinding::inflate) {

    private var todoList = mutableListOf<ModelTask>()
    private val adapterTasks = AdapterTasks()
    private var calendarDay = CalendarDay.today()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalendarListenter()
        refreshTodos()
        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        refreshTodos()
        showBottomAppBarViews()
    }


    override fun onClicks() {
        adapterTasks.onDelete =
            AdapterTasks.OnItemClickListener { todoModel ->
                showDeleteDialog(
                    onPositiveClick = {
                        deletaTask(todoModel)
                    })
            }

        adapterTasks.onDone = AdapterTasks.OnItemClickListener {
            if (it.isDone!!) {
                it.isDone = false
                MyDataBase.dp?.myDao()?.updateTask(it)
            } else {
                it.isDone = true
                MyDataBase.dp?.myDao()?.updateTask(it)
            }
            refreshTodos()

            }


        adapterTasks.onItemClick =
            AdapterTasks.OnItemClickListener { todoModel ->
                if (todoModel.isDone!!)
                    showToast("This task is completed")
                else
                    findNavController().navigate(
                        TaskFragmentDirections.actionTaskFragmentToEditTaskFragment(todoModel)
                    )
            }

        binding.dateView.setOnDateChangedListener { widget, date, selected ->
            calendarDay = date
            refreshTodos() // Refresh tasks after selecting a new date

        }
    }

    private fun initAdapter() {
        adapterTasks.tasksList = todoList
        binding.rvTasks.adapter = adapterTasks
    }

    private fun deletaTask(todoModel: ModelTask) {
        MyDataBase.dp?.myDao()?.deleteTask(todoModel)
        showToast("Task Deleted Successfully")
        refreshTodos()
    }

    private fun initCalendarListenter() {
        binding.dateView.selectedDate = CalendarDay.today()
        binding.dateView.setOnDateChangedListener { widget, date, selected ->
            calendarDay = date
            refreshTodos()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshTodos() {
        val calendar = Calendar.getInstance()
        calendar.setDate(calendarDay.year, calendarDay.month - 1, calendarDay.day)
        calendar.clearTime()
        todoList = MyDataBase.dp?.myDao()?.getTaskByDate(calendar.timeInMillis)?.toMutableList()
            ?: mutableListOf()
        adapterTasks.tasksList = todoList
        adapterTasks.notifyDataSetChanged()
    }
}