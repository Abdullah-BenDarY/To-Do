package com.example.todo.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R.id.addTaskBottomSheet
import com.example.todo.R.id.nav_host_fragment
import com.example.todo.base.BaseActivity
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavController()
    }

    override fun onClicks() {
        binding.fabAddTask.setOnClickListener {
            findNavController(nav_host_fragment).navigate(addTaskBottomSheet)
        }
    }

    private fun setupNavController() {
        val navController = findNavController(nav_host_fragment)
        binding.btmNav.setupWithNavController(navController)
    }

}