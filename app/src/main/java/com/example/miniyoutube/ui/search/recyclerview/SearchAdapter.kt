package com.example.miniyoutube.ui.search.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.data.model.remote.Item
import com.example.miniyoutube.data.model.remote.Snippet
import com.example.miniyoutube.databinding.ViewholderSearchBinding

class SearchAdapter(val onClick: (Item) -> Unit): ListAdapter<Item, SearchAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
    inner class ViewHolder(private val binding : ViewholderSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            Glide.with(itemView.context)
                .load(item.snippet.thumbnails.medium.url)
                .into(binding.titleImage)

            binding.titleText.text = item.snippet.title

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}