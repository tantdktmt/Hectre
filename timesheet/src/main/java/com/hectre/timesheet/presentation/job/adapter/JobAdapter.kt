package com.hectre.timesheet.presentation.job.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hectre.extension.setSafeOnClickListener
import com.hectre.timesheet.BR
import com.hectre.timesheet.databinding.ItemConfirmButtonBinding
import com.hectre.timesheet.databinding.ItemDividerBinding
import com.hectre.timesheet.databinding.ItemJobHeaderBinding
import com.hectre.timesheet.databinding.ItemStaffBinding
import com.hectre.timesheet.presentation.model.BaseListModel
import com.hectre.timesheet.presentation.model.JobHeaderModel
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
            BaseListModel.ViewType.JOB_HEADER -> {
                val dataBinding = ItemJobHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                JobHeaderViewHolder(dataBinding)
            }
            BaseListModel.ViewType.STAFF -> {
                val dataBinding = ItemStaffBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                StaffViewHolder(dataBinding)
            }
            BaseListModel.ViewType.DIVIDER -> {
                val dataBinding = ItemDividerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DividerViewHolder(dataBinding)
            }

            BaseListModel.ViewType.CONFIRM_BUTTON -> {
                val dataBinding = ItemConfirmButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ConfirmButtonViewHolder(dataBinding)
            }
            else -> {
                val dataBinding = ItemJobHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                JobHeaderViewHolder(dataBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JobHeaderViewHolder -> holder.bindData(getItem(position) as JobHeaderModel)
            is StaffViewHolder -> holder.bindData(getItem(position) as StaffModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    private inner class JobHeaderViewHolder(private val binding: ItemJobHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: JobHeaderModel) {
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

            val rowIdAdapter = RowIdAdapter {
                LogUtil.d("Click on row $it.id")
            }
            binding.rvRowId.apply {
                setHasFixedSize(true)
                adapter = rowIdAdapter
            }
            rowIdAdapter.submitList(item.listAvailableRow)

            val rowInfoAdapter = RowInfoAdapter()
            binding.rvRowInfo.adapter = rowInfoAdapter
            rowInfoAdapter.submitList(item.listAssignedRow)

            binding.executePendingBindings()
        }
    }

    private inner class DividerViewHolder(binding: ItemDividerBinding) :
        RecyclerView.ViewHolder(binding.root)

    private inner class ConfirmButtonViewHolder(binding: ItemConfirmButtonBinding) :
        RecyclerView.ViewHolder(binding.root)
}
