package com.example.wooyj.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wooyj.data.RankingListItem
import com.example.wooyj.databinding.VhListItemBinding

/**
 *
 * Created on 2022/09/02.
 *
 * @author wooyj
 *
 */


class RankingListDiffCallBack : DiffUtil.ItemCallback<RankingListItem>() {
    override fun areItemsTheSame(oldItem: RankingListItem, newItem: RankingListItem): Boolean =
        oldItem.rank == newItem.rank

    override fun areContentsTheSame(oldItem: RankingListItem, newItem: RankingListItem): Boolean =
        oldItem == newItem
}


class MyRankingAdapter :
    ListAdapter<RankingListItem, MyRankingAdapter.ViewHolder>(RankingListDiffCallBack()) {

    override fun getItemId(position: Int): Long = getItem(position).rank.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: VhListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RankingListItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VhListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}