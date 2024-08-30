package com.example.todo.ui

import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.todo.R.id.*
import com.example.todo.base.BaseActivity
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onClicks() {
        setupNavController()
        binding.apply {
            fabAddTask.setOnClickListener {
                findNavController(nav_host_fragment).navigate(addTaskBottomSheet)
            }

        }
    }

    private fun setupNavController() {
        val bottomNavigationView = binding.btmNav
        val navController = findNavController(nav_host_fragment)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                nav_tasks -> {
                    if (navController.currentDestination?.id != taskFragment) {
                        navController.navigate(taskFragment)
                        return@setOnItemSelectedListener true
                    }
                }

                nav_settings -> {
                    if (navController.currentDestination?.id != settingsFragment) {
                        navController.navigate(settingsFragment)
                        return@setOnItemSelectedListener true
                    }
                }
            }
            false
        }
        bottomNavigationView.selectedItemId = nav_tasks
        handleBackStack(nav_host_fragment , taskFragment)
    }

    private fun handleBackStack(hostFragment: Int, homeFragment: Int) {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            val navController = findNavController(hostFragment)
            override fun handleOnBackPressed() {
                if (navController.currentDestination?.id == homeFragment) {
                    finish() // Finish the app when on taskFragment
                } else {
                    navController.navigateUp() // Otherwise, navigate up
                }
            }
        })
    }


}
