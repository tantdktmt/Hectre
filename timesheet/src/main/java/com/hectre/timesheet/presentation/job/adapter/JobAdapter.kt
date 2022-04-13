package com.hectre.timesheet.presentation.job.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hectre.extension.setSafeOnClickListener
import com.hectre.timesheet.BR
import com.hectre.timesheet.databinding.ItemHeaderBinding
import com.hectre.timesheet.databinding.ItemStaffBinding
import com.hectre.timesheet.presentation.model.BaseListModel
import com.hectre.timesheet.presentation.model.HeaderModel
import com.hectre.timesheet.presentation.model.StaffModel
import com.hectre.utility.LogUtil

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
            BaseListModel.ViewType.STAFF -> {
                val dataBinding = ItemStaffBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                StaffViewHolder(dataBinding)
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
            is HeaderViewHolder -> holder.bindData(getItem(position) as HeaderModel)
            is StaffViewHolder -> holder.bindData(getItem(position) as StaffModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    private inner class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: HeaderModel) {
            binding.setVariable(BR.item_data, item)
            binding.tvAddMaxTrees.setSafeOnClickListener {
                onClickAddMaxTrees()
            }
            binding.executePendingBindings()
        }
    }

    private inner class StaffViewHolder(private val binding: ItemStaffBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: StaffModel) {
            binding.setVariable(BR.item_data, item)
            val adapter = RowIdAdapter {
                LogUtil.d("Click on row $it.id")
            }
            binding.rvRowId.adapter = adapter
            adapter.submitList(item.listRow)
            binding.executePendingBindings()
        }
    }
}
