package com.example.todo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentEditTaskBinding


class EditTaskFragment : BaseFragment<FragmentEditTaskBinding>(FragmentEditTaskBinding::inflate) {

    private val args by navArgs<EditTaskFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onClicks() {

    }

}
