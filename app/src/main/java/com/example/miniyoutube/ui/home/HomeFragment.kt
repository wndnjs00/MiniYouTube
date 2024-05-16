package com.example.miniyoutube.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.miniyoutube.R
import com.example.miniyoutube.ui.home.recyclerview.CategorySpinnerAdapter
import com.example.miniyoutube.databinding.FragmentHomeBinding
import com.example.miniyoutube.ui.home.recyclerview.HomeRecyclerViewAdapter
import com.example.miniyoutube.ui.home.recyclerview.YoutubeImageClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerViewAdapter
    private val homeViewModle: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModle.requestVideo("0")

        setSpinner()
    }


    private fun setSpinner() {

        val list = listOf<String>("1", "2", "3")

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
                    Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //선택되지 않은 경우
                }
            }
    }

    private fun setUpObserve() {
        homeViewModle.youtubeVideo.observe(viewLifecycleOwner) {

        }
    }

    private fun homeRecyclerViewAdapter() {
        HomeRecyclerViewAdapter(youtubeImageClickListener = object : YoutubeImageClickListener {
            override fun onClickItem(youtubeVideoList: View) {

            }
        })
    }
}