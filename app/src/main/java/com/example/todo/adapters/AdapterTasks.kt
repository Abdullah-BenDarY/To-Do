package com.example.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.R.color.green
import com.example.todo.R.color.primary_blue
import com.example.todo.R.drawable
import com.example.todo.R.drawable.*
import com.example.todo.dataBase.moel.ModelTask
import com.example.todo.databinding.ItemTaskBinding
import com.example.todo.util.formatTimeOnly

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
        holder.checkIsDone(data.isDone)
    }


    inner class Holder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun checkIsDone(isDone: Boolean?) {
            val green = ContextCompat.getColor(itemView.context, green)
            val blue = ContextCompat.getColor(itemView.context, primary_blue)
            val done = ContextCompat.getDrawable(itemView.context, img_done)
            val check = ContextCompat.getDrawable(itemView.context, ic_check_mark)
            if (isDone!!)
                binding.apply {
                    title.setTextColor(green)
                    draggingBar.drawable.setTint(green)
                    btnTaskIsDone.background =done
                }
            else{
                binding.apply {
                    title.setTextColor(blue)
                    draggingBar.drawable.setTint(blue)
                    btnTaskIsDone.background =check
                }
            }
        }
        fun bind(task: ModelTask) {
            binding.apply {
                title.text = task.title
                time.text = task.time!!.formatTimeOnly()

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

    fun interface OnItemClickListener {
        fun onClick(todoModel: ModelTask)


    }

}