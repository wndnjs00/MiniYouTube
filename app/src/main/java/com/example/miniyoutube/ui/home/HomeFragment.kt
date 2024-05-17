package com.example.miniyoutube.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.miniyoutube.R
import com.example.miniyoutube.data.model.remote.TrendItem
import com.example.miniyoutube.databinding.FragmentHomeBinding
import com.example.miniyoutube.ui.home.recyclerview.CategoryRecyclerViewAdapter
import com.example.miniyoutube.ui.home.recyclerview.CategorySpinnerAdapter
import com.example.miniyoutube.ui.home.recyclerview.HeadingRecyclerViewAdapter
import com.example.miniyoutube.ui.home.recyclerview.HomeImageClickListener
import com.example.miniyoutube.ui.model.FavoriteItem
import com.example.miniyoutube.ui.videodetail.VideoDetailActivity
import com.example.miniyoutube.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var headingRecyclerViewAdapter: HeadingRecyclerViewAdapter
    private lateinit var categoryRecyclerViewAdapter: CategoryRecyclerViewAdapter
    private val homeViewModle: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headingRecyclerViewAdapter = headingRecyclerViewAdapter()
        categoryRecyclerViewAdapter = categoryRecyclerViewAdapter()

        homeViewModle.requestVideo("0",SelectVideo.HEADING)
        binding.rcHeadingView.adapter = headingRecyclerViewAdapter
        setSpinner()
        setUpObserve()
    }


    private fun setSpinner() {
        val list = listOf<String>("Sports", "Gaming", "Music", "Pets&Animals", "Howto&Style")
        binding.spHomeBackground.adapter =
            CategorySpinnerAdapter(requireContext(), R.layout.item_spinner_home, list)
        binding.spHomeBackground.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {//선택
                    val value = binding.spHomeBackground.getItemAtPosition(position).toString()
                    when (position) {
                        0 -> {
                            homeViewModle.requestVideo("17", SelectVideo.CATEGORY)
                        }

                        1 -> {
                            homeViewModle.requestVideo("20", SelectVideo.CATEGORY)
                        }

                        2 -> {
                            homeViewModle.requestVideo("10", SelectVideo.CATEGORY)
                        }

                        3 -> {
                            homeViewModle.requestVideo("15", SelectVideo.CATEGORY)
                        }

                        4 -> {
                            homeViewModle.requestVideo("26", SelectVideo.CATEGORY)
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //선택되지 않은 경우
                }
            }
        binding.rcSearchView.adapter = categoryRecyclerViewAdapter
    }

    private fun setUpObserve() {
        homeViewModle.mostPopularVideo.observe(viewLifecycleOwner) {
            it.items?.let {
                headingRecyclerViewAdapter.headingsubmitList(it)
            }
        }
        homeViewModle.categoryVideo.observe(viewLifecycleOwner){
            it.items?.let {
                categoryRecyclerViewAdapter.categorysubmitList(it)
            }
        }
    }

    private fun headingRecyclerViewAdapter(): HeadingRecyclerViewAdapter {
        return HeadingRecyclerViewAdapter(object : HomeImageClickListener {
            override fun onClickItem(youtubeVideoList: TrendItem) {

                putIntentData(youtubeVideoList)

            }
        })
    }

    private fun categoryRecyclerViewAdapter(): CategoryRecyclerViewAdapter {
        return CategoryRecyclerViewAdapter(object : HomeImageClickListener {
            override fun onClickItem(youtubeVideoList: TrendItem) {

                putIntentData(youtubeVideoList)

            }
        })
    }

    private fun putIntentData(youtubeVideoList: TrendItem){

        val resultItem = FavoriteItem(
            videoId = youtubeVideoList.id,
            channelId = youtubeVideoList.snippet.channelId,
            title = youtubeVideoList.snippet.title,
            description = youtubeVideoList.snippet.description,
            url = youtubeVideoList.snippet.thumbnails.medium.url
        )

        val intent = Intent(context, VideoDetailActivity::class.java)
        intent.putExtra(Constants.FAVORITE_ITEM_KEY, resultItem)
        startActivity(intent)
    }
}