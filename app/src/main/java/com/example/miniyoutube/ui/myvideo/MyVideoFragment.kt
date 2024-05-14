package com.example.miniyoutube.ui.myvideo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.miniyoutube.VideoDetailActivity
import com.example.miniyoutube.databinding.FragmentMyVideoBinding
import com.example.miniyoutube.ui.myvideo.recyclerview.MyVideoAdapter

class MyVideoFragment : Fragment() {

    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

    private val myVideoAdapter: MyVideoAdapter by lazy { MyVideoAdapter(onMyVideoClick = ::goVideoDetail) }

    private fun goVideoDetail(fakeMyVideoData: FakeMyVideoData) {
        startActivity(Intent(requireActivity(), VideoDetailActivity::class.java).apply {
            this.putExtra("fakeMyVideoData", fakeMyVideoData)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyVideoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSearchAdapter()
        setupData()
        setupListener()
    }

    private fun setupData() {
        myVideoAdapter.submitList(
            listOf(
                FakeMyVideoData("url1", "titl1"),
                FakeMyVideoData("url2", "titl2"),
                FakeMyVideoData("url3", "titl3"),
                FakeMyVideoData("url4", "titl4"),
                FakeMyVideoData("url5", "titl5"),
            )
        )
    }

    private fun setupListener() {

    }

    private fun setSearchAdapter() {
        val myVideoManager =
            GridLayoutManager(requireActivity(), 2)
        binding.rv.apply {
            layoutManager = myVideoManager
            adapter = myVideoAdapter
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}