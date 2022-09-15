package com.mmb.happybox.cheerup.happythingslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mmb.happybox.cheerup.databinding.HappyThingItemBinding
import com.mmb.happybox.model.HappyThing

class HappyThingsPagingAdapter(
    private val onEditClicked: (Long) -> Unit,
    private val onDeleteClicked: (Long) -> Unit,
) : PagingDataAdapter<HappyThing, HappyThingsPagingAdapter.HappyThingViewHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HappyThingViewHolder {
        val binding = HappyThingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return HappyThingViewHolder(
            binding = binding,
            onEditClicked = onEditClicked,
            onDeleteClicked = onDeleteClicked
        )
    }

    override fun onBindViewHolder(holder: HappyThingViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class HappyThingViewHolder(
        private val binding: HappyThingItemBinding,
        private val onEditClicked: (Long) -> Unit,
        private val onDeleteClicked: (Long) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(happyThing: HappyThing) {
            binding.apply {
                name = happyThing.name
                onEditClicked = {
                    onEditClicked(happyThing.id)
                }
                onDeleteClicked = {
                    onDeleteClicked(happyThing.id)
                }
                executePendingBindings()
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<HappyThing>() {
            override fun areItemsTheSame(oldItem: HappyThing, newItem: HappyThing): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HappyThing, newItem: HappyThing): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

}