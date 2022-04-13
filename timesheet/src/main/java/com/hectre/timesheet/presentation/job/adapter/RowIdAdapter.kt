package com.hectre.timesheet.presentation.job.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hectre.extension.setSafeOnClickListener
import com.hectre.timesheet.BR
import com.hectre.timesheet.databinding.ItemRowIdBinding
import com.hectre.timesheet.presentation.model.Row

class RowIdAdapter(
    private val onClickItem: (Row) -> Unit
) : ListAdapter<Row, RowIdAdapter.ViewHolder>(itemDiff) {

    companion object {

        private val itemDiff = object : DiffUtil.ItemCallback<Row>() {

            override fun areItemsTheSame(
                oldItem: Row,
                newItem: Row
            ) = oldItem === newItem // CHECK AGAIN

            override fun areContentsTheSame(
                oldItem: Row,
                newItem: Row
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowIdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemRowIdBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: Row) {
            binding.setVariable(BR.item_data, item)
            binding.root.setSafeOnClickListener {
                onClickItem(item)
            }
            binding.executePendingBindings()
        }
    }
}
