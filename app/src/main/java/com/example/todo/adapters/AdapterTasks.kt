package com.example.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.dataBase.moel.ModelTask
import com.example.todo.databinding.ItemTaskBinding

class AdapterTasks() : RecyclerView.Adapter<AdapterTasks.Holder>() {
    var tasksList: MutableList<ModelTask>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = tasksList?.size ?: 0


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = tasksList!![position]
        holder.bind(data)
    }


    private lateinit var onClick: (Int) -> Unit?
    fun setOnClick(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }

    fun addItem() {
        notifyItemInserted(tasksList?.size?: 0)
    }

    inner class Holder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick.invoke(tasksList!![layoutPosition].id)
            }
        }

        fun bind(task: ModelTask) {
            binding.apply {
                title.text = task.title
                time.text = task.time.toString()
            }
        }

    }

}