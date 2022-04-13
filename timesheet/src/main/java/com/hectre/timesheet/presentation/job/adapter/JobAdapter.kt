package com.hectre.timesheet.presentation.job.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hectre.extension.setSafeOnClickListener
import com.hectre.timesheet.BR
import com.hectre.timesheet.databinding.ItemHeaderBinding
import com.hectre.timesheet.presentation.model.BaseListModel
import com.hectre.timesheet.presentation.model.ListHeaderModel

class JobAdapter(
    private val onClickAddMaxTrees: () -> Unit,
    private val onClickApplyToAll: () -> Unit
) : ListAdapter<BaseListModel, RecyclerView.ViewHolder>(itemDiff) {

    companion object {

        private val itemDiff = object : DiffUtil.ItemCallback<BaseListModel>() {

            override fun areItemsTheSame(
                oldItem: BaseListModel,
                newItem: BaseListModel
            ) = oldItem === newItem // CHECK AGAIN

            override fun areContentsTheSame(
                oldItem: BaseListModel,
                newItem: BaseListModel
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BaseListModel.ViewType.HEADER -> {
                val dataBinding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(dataBinding)
            }
            else -> {
                val dataBinding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(dataBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindData(getItem(position) as ListHeaderModel)
        }
    }

    private inner class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: ListHeaderModel) {
            binding.setVariable(BR.item_data, item)
            binding.tvAddMaxTrees.setSafeOnClickListener {
                onClickAddMaxTrees()
            }
            binding.executePendingBindings()
        }
    }

    object ViewType {

        const val UNDEFINED = -1
        const val HEADER = 0
        const val NORMAL_ITEM = 1
        const val LAST_ITEM = 2
        const val DIVIDER = 3
        const val CONFIRM_BUTTON = 4
    }
}
