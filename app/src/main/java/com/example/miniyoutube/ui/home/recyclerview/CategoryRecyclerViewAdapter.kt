package com.example.miniyoutube.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.data.model.remote.TrendItem
import com.example.miniyoutube.databinding.ItemListVarBinding

class CategoryRecyclerViewAdapter(
    private val homeImageClickListener: HomeImageClickListener
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {

    var youtubeVideoList: List<TrendItem> = listOf()

    class CategoryViewHolder(
        private var binding: ItemListVarBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(youtubeVideoList: TrendItem) {
            Glide.with(binding.root.context).load(youtubeVideoList.snippet.thumbnails.medium.url)
                .into(binding.ivProfile)
            binding.tvTitle.text = youtubeVideoList.snippet.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(
            binding = ItemListVarBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.youtubeVideoList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(youtubeVideoList[position])
        holder.itemView.setOnClickListener {
            homeImageClickListener.onClickItem(youtubeVideoList[position])
        }
    }

    fun categorysubmitList(item: List<TrendItem>) {
        this.youtubeVideoList = item
        notifyDataSetChanged()
    }
}