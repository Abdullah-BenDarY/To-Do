package com.example.todo.ui.tasks

import android.os.Bundle
import android.view.View
import com.example.todo.adapters.AdapterTasks
import com.example.todo.base.BaseFragment
import com.example.todo.dataBase.MyDataBase
import com.example.todo.dataBase.moel.ModelTask
import com.example.todo.databinding.FragmentTaskBinding
import com.example.todo.util.showToast

class TaskFragment : BaseFragment<FragmentTaskBinding>(FragmentTaskBinding::inflate) {

    private var todoList = mutableListOf<ModelTask>()
    private val adapterTasks = AdapterTasks()
    private val dataBase = MyDataBase


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        refreshTodos()
    }


    override fun onClicks() {
        adapterTasks.setOnClick {
            showToast("fhgdfkjgm")
        }
    }

    private fun initAdapter() {
        adapterTasks.tasksList = todoList
        binding.rvTasks.adapter = adapterTasks
    }

     fun refreshTodos(){
        todoList.addAll(dataBase.dp?.myDao()?.getAllTask()!!)
        adapterTasks.addItem()
    }
}