package com.hectre.timesheet.presentation.job.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
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
                val binding = ItemJobHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                JobHeaderViewHolder(binding)
            }
            BaseListModel.ViewType.STAFF -> {
                val binding = ItemStaffBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                val rowIdLayoutManager = LinearLayoutManager(
                    parent.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                ).apply {
                    initialPrefetchItemCount = 8
                }
                binding.rvRowId.apply {
                    layoutManager = rowIdLayoutManager
                    setHasFixedSize(true)
                }
                val rowInfoLayoutManager =
                    LinearLayoutManager(parent.context, LinearLayoutManager.VERTICAL, false).apply {
                        initialPrefetchItemCount = 5
                    }
                binding.rvRowInfo.apply {
                    layoutManager = rowInfoLayoutManager
                }
                StaffViewHolder(binding)
            }
            BaseListModel.ViewType.DIVIDER -> {
                val binding = ItemDividerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DividerViewHolder(binding)
            }

            BaseListModel.ViewType.CONFIRM_BUTTON -> {
                val binding = ItemConfirmButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ConfirmButtonViewHolder(binding)
            }
            else -> {
                val binding = ItemJobHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                JobHeaderViewHolder(binding)
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

            val rowInfoAdapter = RowInfoAdapter()
            binding.rvRowInfo.adapter = rowInfoAdapter
            rowInfoAdapter.submitList(item.listAssignedRow)

            val rowIdAdapter = RowIdAdapter { row, adapter ->
                LogUtil.d("Click on row $row.id")
                item.updateListAvailableRow(row)
                adapter.submitList(item.listAvailableRow)
                rowInfoAdapter.submitList(item.listAssignedRow)
            }
            binding.rvRowId.adapter = rowIdAdapter
            rowIdAdapter.submitList(item.listAvailableRow)

            binding.executePendingBindings()
        }
    }

    private inner class DividerViewHolder(binding: ItemDividerBinding) :
        RecyclerView.ViewHolder(binding.root)

    private inner class ConfirmButtonViewHolder(binding: ItemConfirmButtonBinding) :
        RecyclerView.ViewHolder(binding.root)
}
