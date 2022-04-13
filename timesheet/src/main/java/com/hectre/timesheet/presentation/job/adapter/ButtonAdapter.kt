package com.hectre.timesheet.presentation.job.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hectre.extension.setSafeOnClickListener
import com.hectre.timesheet.databinding.ItemButtonBinding

class ButtonAdapter(private val onItemClick: () -> Unit) :
    RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemButtonBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1

    inner class ViewHolder(private val binding: ItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.btConfirm.setSafeOnClickListener {
                onItemClick.invoke()
            }
        }
    }
}
