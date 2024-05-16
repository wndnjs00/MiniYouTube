package com.example.miniyoutube.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.data.model.remote.Item
import com.example.miniyoutube.data.model.remote.Snippet
import com.example.miniyoutube.data.model.remote.TrendItem
import com.example.miniyoutube.databinding.ItemListMainTitleBinding

interface YoutubeImageClickListener {
    fun onClickItem(youtubeVideoList: View)
}

class HomeRecyclerViewAdapter(
    private val youtubeImageClickListener: YoutubeImageClickListener
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>() {

    var youtubeVideoList: List<TrendItem> = listOf()

    class HomeViewHolder(
        private val binding: ItemListMainTitleBinding,
        private val youtubeImageClickListener: YoutubeImageClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    youtubeImageClickListener.onClickItem(youtubeVideoList = it)
                }
            }

        fun bind(youtubeVideoList: TrendItem) {
            Glide.with(binding.root.context).load(youtubeVideoList.snippet.thumbnails)
                .into(binding.ivProfile)
            binding.tvTitle.text = youtubeVideoList.snippet.channelTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(binding = ItemListMainTitleBinding.inflate(layoutInflater,parent,false),
        youtubeImageClickListener = youtubeImageClickListener
        )
    }

    override fun getItemCount(): Int {
        return youtubeVideoList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(youtubeVideoList[position])
    }

    fun submitList(item: List<TrendItem>) {
        this.youtubeVideoList = item
        notifyDataSetChanged()
    }
}