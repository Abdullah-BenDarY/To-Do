package com.example.todo.util

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.showToast (massage : Any?) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_LONG).show()





}