package com.example.miniyoutube.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.data.model.remote.TrendItem
import com.example.miniyoutube.databinding.ItemListChannelTitleBinding

class CategoryChannelRecyclerViewAdapter(
    private val homeImageClickListener: HomeImageClickListener
) : RecyclerView.Adapter<CategoryChannelRecyclerViewAdapter.CategoryChannelViewHolder>() {

    var youtubeVideoList: List<TrendItem> = listOf()
    class CategoryChannelViewHolder(
        private var binding: ItemListChannelTitleBinding,
        private val homeImageClickListener: HomeImageClickListener
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                homeImageClickListener.onClickItem(youtubeVideoList = it)
            }
        }

        fun bind(youtubeVideoList: TrendItem) {
            Glide.with(binding.root.context).load(youtubeVideoList.snippet.thumbnails.medium.url)
                .into(binding.ivProfile)
            binding.tvTitle.text = youtubeVideoList.snippet.channelId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryChannelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoryChannelViewHolder(
            binding = ItemListChannelTitleBinding.inflate(layoutInflater,parent,false),
            homeImageClickListener = homeImageClickListener
        )
    }

    override fun getItemCount(): Int {
        return this.youtubeVideoList.size
    }

    override fun onBindViewHolder(holder: CategoryChannelViewHolder, position: Int) {
        holder.bind(youtubeVideoList[position])
    }

    fun categoryCannelsubmitList(item: List<TrendItem>){
        this.youtubeVideoList = item
        notifyDataSetChanged()
    }
}