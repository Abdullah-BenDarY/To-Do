package com.example.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.dataBase.moel.ModelTask
import com.example.todo.databinding.ItemTaskBinding

class AdapterTasks() : RecyclerView.Adapter<AdapterTasks.Holder>() {
    var tasksList: List<ModelTask>? = null
    var onItemClick: OnItemClickListener? = null
    var onDelete: OnItemClickListener? = null
    var onDone: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = tasksList?.size ?: 0


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = tasksList!![position]
        onClicks(holder, data)
        holder.bind(data)
    }


    inner class Holder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: ModelTask) {
            binding.apply {
                title.text = task.title
                time.text = task.time.toString()

            }
        }
    }


    fun onClicks(holder: Holder, data: ModelTask) {
        holder.binding.leftView.setOnClickListener {
            onDelete?.onClick(data)
        }
        holder.binding.btnTaskIsDone.setOnClickListener {
            onDone?.onClick(data)
        }
        holder.binding.todoItem.setOnClickListener {
            onItemClick?.onClick(data)
        }

    }

    interface OnItemClickListener {
        fun onClick(todoModel: ModelTask)


    }

}