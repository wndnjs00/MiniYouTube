package com.example.miniyoutube.ui.home

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
import com.example.miniyoutube.ui.home.recyclerview.CategoryChannelRecyclerViewAdapter
import com.example.miniyoutube.ui.home.recyclerview.CategoryRecyclerViewAdapter
import com.example.miniyoutube.ui.home.recyclerview.CategorySpinnerAdapter
import com.example.miniyoutube.ui.home.recyclerview.HeadingRecyclerViewAdapter
import com.example.miniyoutube.ui.home.recyclerview.HomeImageClickListener
import com.example.miniyoutube.ui.model.FavoriteItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var headingRecyclerViewAdapter: HeadingRecyclerViewAdapter
    private lateinit var categoryRecyclerViewAdapter: CategoryRecyclerViewAdapter
    private lateinit var categoryChannelRecyclerViewAdapter: CategoryChannelRecyclerViewAdapter
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
        categoryChannelRecyclerViewAdapter = categoryChannelRecyclerViewAdapter()


        homeViewModle.requestVideo("0") //요청(0 = 디폴트)
        homeViewModle.requestVideo("17") //마지막 데이터를 업데이트 (같은 requestVideo 사용


        setSpinner()
        binding.rcHeadingView.adapter = headingRecyclerViewAdapter
        setUpObserve()


        binding.rcCategoryView.adapter = categoryChannelRecyclerViewAdapter
    }


    private fun setSpinner() {
        val list = listOf<String>("Sports", "Movies", "Music", "Pets&Animals", "Drama")
        //api 호출해서 오는 아이템 > 뷰모델
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
                            homeViewModle.requestVideo("17")
                        }

                        1 -> {
                            homeViewModle.requestVideo("30")
                        }

                        2 -> {
                            homeViewModle.requestVideo("10")
                        }

                        3 -> {
                            homeViewModle.requestVideo("15")
                        }

                        4 -> {
                            homeViewModle.requestVideo("36")
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //선택되지 않은 경우
                }
            }
        setUpObserve()
        binding.rcSearchView.adapter = categoryRecyclerViewAdapter
        //화면 전환 > 값이 바뀜 > 리사이클려뷰 갱신
    }

    private fun setUpObserve() {
        homeViewModle.youtubeVideo.observe(viewLifecycleOwner) {
            //데이터가 변경될 때 (데이터가 들어왔을때)
            //요청 >  < 데이터를 가져옴 + observe
            it.items?.let {
                headingRecyclerViewAdapter.headingsubmitList(it)
                categoryRecyclerViewAdapter.categorysubmitList(it)
            }
        }
    }

    private fun headingRecyclerViewAdapter(): HeadingRecyclerViewAdapter {
        return HeadingRecyclerViewAdapter(object : HomeImageClickListener {
            override fun onClickItem(youtubeVideoList: View) {

            }
        })
    }

    private fun categoryRecyclerViewAdapter(): CategoryRecyclerViewAdapter {
        return CategoryRecyclerViewAdapter(object : HomeImageClickListener {
            override fun onClickItem(youtubeVideoList: View) {
                //
            }
        })
    }

    private fun categoryChannelRecyclerViewAdapter(): CategoryChannelRecyclerViewAdapter {
        return CategoryChannelRecyclerViewAdapter(object : HomeImageClickListener{
            override fun onClickItem(youtubeVideoList: View) {
                //
            }
        })
    }
}