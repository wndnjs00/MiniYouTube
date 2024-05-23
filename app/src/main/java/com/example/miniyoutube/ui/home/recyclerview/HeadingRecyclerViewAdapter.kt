package com.example.miniyoutube.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miniyoutube.data.model.remote.videoinfo.TrendItem
import com.example.miniyoutube.databinding.ItemListHoriBinding

interface HomeImageClickListener {
    fun onClickItem(youtubeVideoList: TrendItem)
}

class HeadingRecyclerViewAdapter(
    private val homeImageClickListener: HomeImageClickListener
) : RecyclerView.Adapter<HeadingRecyclerViewAdapter.HeadingViewHolder>() {

    var youtubeVideoList: List<TrendItem> = listOf()


    class HeadingViewHolder(
        private val binding: ItemListHoriBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(youtubeVideoList: TrendItem) {
            Glide.with(binding.root.context).load(youtubeVideoList.snippet.thumbnails.medium.url)
                .into(binding.ivProfile)
            binding.tvTitle.text = youtubeVideoList.snippet.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeadingViewHolder(
            binding = ItemListHoriBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return youtubeVideoList.size
    }

    override fun onBindViewHolder(holder: HeadingViewHolder, position: Int) {
        holder.bind(youtubeVideoList[position])
        holder.itemView.setOnClickListener {
            homeImageClickListener.onClickItem(youtubeVideoList[position])
        }
    }

    fun headingsubmitList(item: List<TrendItem>) {
        this.youtubeVideoList = item
        notifyDataSetChanged()
    }
}