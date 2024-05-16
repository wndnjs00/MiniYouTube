package com.example.miniyoutube.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.data.model.remote.TrendItem
import com.example.miniyoutube.databinding.ItemListMainTitleBinding

interface HomeImageClickListener {
    fun onClickItem(youtubeVideoList: View)
}

class HeadingRecyclerViewAdapter(
    private val homeImageClickListener: HomeImageClickListener
) : RecyclerView.Adapter<HeadingRecyclerViewAdapter.HeadingViewHolder>() {

    var youtubeVideoList: List<TrendItem> = listOf()

    class HeadingViewHolder(
        private val binding: ItemListMainTitleBinding,
        private val homeImageClickListener: HomeImageClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeadingViewHolder(binding = ItemListMainTitleBinding.inflate(layoutInflater,parent,false),
            homeImageClickListener = homeImageClickListener
        )
    }

    override fun getItemCount(): Int {
        return youtubeVideoList.size
    }

    override fun onBindViewHolder(holder: HeadingViewHolder, position: Int) {
        holder.bind(youtubeVideoList[position])
    }

    fun headingsubmitList(item: List<TrendItem>) {
        this.youtubeVideoList = item
        notifyDataSetChanged()
    }
}