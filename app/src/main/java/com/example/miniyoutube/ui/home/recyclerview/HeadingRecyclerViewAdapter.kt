package com.example.miniyoutube.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.data.model.remote.TrendItem
import com.example.miniyoutube.databinding.ItemListMainTitleBinding

interface HeadingImageClickListener {
    fun onClickItem(youtubeVideoList: View)
}

class HeadingRecyclerViewAdapter(
    private val headingImageClickListener: HeadingImageClickListener
) : RecyclerView.Adapter<HeadingRecyclerViewAdapter.HomeViewHolder>() {

    var youtubeVideoList: List<TrendItem> = listOf()

    class HomeViewHolder(
        private val binding: ItemListMainTitleBinding,
        private val headingImageClickListener: HeadingImageClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    headingImageClickListener.onClickItem(youtubeVideoList = it)
                }
            }

        fun bind(youtubeVideoList: TrendItem) {
            Glide.with(binding.root.context).load(youtubeVideoList.snippet.thumbnails.medium.url)
                .into(binding.ivProfile)
            binding.tvTitle.text = youtubeVideoList.snippet.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(binding = ItemListMainTitleBinding.inflate(layoutInflater,parent,false),
            headingImageClickListener = headingImageClickListener
        )
    }

    override fun getItemCount(): Int {
        return youtubeVideoList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(youtubeVideoList[position])
    }

    fun headingsubmitList(item: List<TrendItem>) {
        this.youtubeVideoList = item
        notifyDataSetChanged()
    }
}