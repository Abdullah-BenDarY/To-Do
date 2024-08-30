package com.example.todo.ui

import android.os.Bundle
import com.example.todo.R
import com.example.todo.base.BaseBottomSheet
import com.example.todo.databinding.BottomSheetAddTaskBinding


class AddTaskBottomSheet :
    BaseBottomSheet<BottomSheetAddTaskBinding>(BottomSheetAddTaskBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onClicks() {
        binding.apply {
            selectDateTv.setOnClickListener {

            }

            selectTimeTv.setOnClickListener {

            }
            addTaskBtn.setOnClickListener {

            }
            title.setOnClickListener {

            }
            description.setOnClickListener {

            }

        }

    }
}