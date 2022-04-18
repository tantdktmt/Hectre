package com.hectre.timesheet.presentation.job.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hectre.timesheet.BR
import com.hectre.timesheet.databinding.ItemRowInfoBinding
import com.hectre.timesheet.presentation.model.RowModel
import com.hectre.utility.CharRestrictionTextWatcher
import com.hectre.utility.ViewUtil

class RowInfoAdapter : ListAdapter<RowModel, RowInfoAdapter.ViewHolder>(itemDiff) {

    companion object {

        private val itemDiff = object : DiffUtil.ItemCallback<RowModel>() {

            override fun areItemsTheSame(
                oldItem: RowModel,
                newItem: RowModel
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RowModel,
                newItem: RowModel
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemRowInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val textWatcher = CharRestrictionTextWatcher()

        fun bindData(item: RowModel) = with(binding) {
            setVariable(BR.item_data, item)
            executePendingBindings()
            etTreesAssignedToStaff.apply {
                removeTextChangedListener(textWatcher)
                addTextChangedListener(textWatcher.attachEditText(this))
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        setSelection(text?.length ?: return@setOnFocusChangeListener)
                    }
                }
            }
            tvTotalTrees.setOnClickListener {
                ViewUtil.showKeyboard(etTreesAssignedToStaff)
            }
        }
    }
}
