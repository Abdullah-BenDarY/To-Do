package com.example.todo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.base.BaseBottomSheet
import com.example.todo.databinding.FragmentAddTaskBottomSheetBinding


class AddTaskBottomSheet: BaseBottomSheet<FragmentAddTaskBottomSheetBinding>(FragmentAddTaskBottomSheetBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onClicks() {
    }
}