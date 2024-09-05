package com.example.todo.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TasksViewModel : ViewModel() {
    private val _taskAdded = MutableLiveData<Boolean>()
    val taskAdded: LiveData<Boolean> get() = _taskAdded

    fun notifyTaskAdded() {
        _taskAdded.value = true
    }

    fun resetTaskAddedFlag() {
        _taskAdded.value = false
    }
}