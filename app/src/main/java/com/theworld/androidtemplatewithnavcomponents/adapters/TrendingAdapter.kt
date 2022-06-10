package com.theworld.androidtemplatewithnavcomponents.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.theworld.androidtemplatewithnavcomponents.R
import com.theworld.androidtemplatewithnavcomponents.data.remote.TrendingItem
import com.theworld.androidtemplatewithnavcomponents.data.remote.TrendingItemRoom
import com.theworld.androidtemplatewithnavcomponents.databinding.LayoutDataBinding

class TrendingAdapter() :
    ListAdapter<TrendingItemRoom, TrendingAdapter.CustomerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding =
            LayoutDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    inner class CustomerViewHolder(private val binding: LayoutDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {


            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                    getItem(position).isSelected = !getItem(position).isSelected
                    notifyItemChanged(position)
                }
            }
        }


        fun bind(item: TrendingItemRoom) {

            binding.apply {
                tvName.text = item.repo

                tvDescription.isVisible = item.desc.isNotBlank()
                tvDescription.text = item.desc
                tvLangName.text = item.lang
                tvFork.text = item.forks
                tvStars.text = item.stars
                tvLink.text = item.repoLink

                containerHidden.isVisible = item.isSelected

                image.load(item.avatar) {
                    error(R.drawable.ic_launcher_foreground)
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TrendingItemRoom>() {
        override fun areItemsTheSame(old: TrendingItemRoom, aNew: TrendingItemRoom) =
            old.id == aNew.id

        override fun areContentsTheSame(old: TrendingItemRoom, aNew: TrendingItemRoom) =
            old == aNew
    }


}