package com.example.miniyoutube.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.miniyoutube.R
import com.example.miniyoutube.databinding.FragmentHomeBinding
import com.example.miniyoutube.ui.home.recyclerview.CategorySpinnerAdapter
import com.example.miniyoutube.ui.home.recyclerview.HeadingImageClickListener
import com.example.miniyoutube.ui.home.recyclerview.HeadingRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var headingRecyclerViewAdapter: HeadingRecyclerViewAdapter
    private val homeViewModle: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModle.requestVideo("0") // 요청

        headingRecyclerViewAdapter = headingRecyclerViewAdapter()

        setSpinner()
        binding.rcHeadingView.adapter = headingRecyclerViewAdapter
        setUpObserve()
    }


    private fun setSpinner() {
        val list = listOf<String>("영화","","2","3","4","5")
        //val list = listOf<>(homeViewModle.requestSearch())
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
                ) {
                    val value = binding.spHomeBackground.getItemAtPosition(position).toString()
                    when(position){
                        0 -> {
                            homeViewModle.requestVideo("17")
                        }
                    }
                    //선택
                    //뷰홀더, 어뎁터 하나 더 생성 후 연결 (레이아웃 동일
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //선택되지 않은 경우
                }
            }
        setUpObserve()
        //화면 전환 > 값이 바뀜 > 리사이클려뷰 갱신
    }

    private fun setUpObserve() {
        homeViewModle.youtubeVideo.observe(viewLifecycleOwner) {
            //데이터가 변경될 때 (데이터가 들어왔을때)
            //요청 >  < 데이터를 가져옴 + observe
            it.items?.let {
                headingRecyclerViewAdapter.headingsubmitList(it)
            }
        }
    }

    private fun headingRecyclerViewAdapter(): HeadingRecyclerViewAdapter {
        return HeadingRecyclerViewAdapter(object : HeadingImageClickListener {
            override fun onClickItem(youtubeVideoList: View) {
                //
            }
        })
    }
}