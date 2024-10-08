package com.example.todo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.todo.dataBase.moel.ModelTask

abstract class BaseFragment <VB :
ViewBinding>(private val bindingInflater : (inflater: LayoutInflater) -> VB ) : Fragment() {

    private var _binding: VB? = null
    protected val binding :VB get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
    }

    open fun onClicks() {
    }
    open fun onClicks(task:ModelTask) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}