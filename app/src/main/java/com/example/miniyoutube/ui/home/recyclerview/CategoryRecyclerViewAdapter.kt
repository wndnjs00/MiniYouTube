package com.example.miniyoutube.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.data.model.remote.Item
import com.example.miniyoutube.data.model.remote.TrendItem
import com.example.miniyoutube.databinding.ItemListMainTitleBinding

class CategoryRecyclerViewAdapter(
    private val homeImageClickListener: HomeImageClickListener
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {

    var youtubeVideoList: List<TrendItem> = listOf()

    class CategoryViewHolder(
        private var binding: ItemListMainTitleBinding,
        private val homeImageClickListener: HomeImageClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                homeImageClickListener.onClickItem(youtubeVideoList = it)
            }
        }

        fun bind(youtubeVideoList: TrendItem) {
            Glide.with(binding.root.context).load(youtubeVideoList.snippet.thumbnails.medium.url)
                .into(binding.ivProfile)
            binding.tvTitle.text = youtubeVideoList.snippet.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(
            binding = ItemListMainTitleBinding.inflate(layoutInflater, parent, false),
            homeImageClickListener = homeImageClickListener
        )
    }

    override fun getItemCount(): Int {
        return this.youtubeVideoList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        return holder.bind(youtubeVideoList[position])
    }

    fun categorysubmitList(item: List<TrendItem>){
        this.youtubeVideoList = item
        notifyDataSetChanged()
    }
}