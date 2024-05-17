package com.example.miniyoutube.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.miniyoutube.ui.main.MainActivity
import com.example.miniyoutube.R
import com.example.miniyoutube.data.model.remote.searchvideo.YoutubeVideo
import com.example.miniyoutube.databinding.FragmentSearchBinding
import com.example.miniyoutube.ui.model.FavoriteItem
import com.example.miniyoutube.ui.search.recyclerview.SearchAdapter
import com.example.miniyoutube.ui.videodetail.VideoDetailActivity
import com.example.miniyoutube.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding : FragmentSearchBinding? = null
    private lateinit var mainActivity: MainActivity
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is MainActivity) mainActivity = context
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }


    private fun initViews() {
        adapter = SearchAdapter(onClick = {
            val resultList = FavoriteItem(
                videoId = it.id.videoId,
                channelId = it.snippet.channelId,
                title = it.snippet.title,
                description = it.snippet.description,
                url = it.snippet.thumbnails.medium.url
            )

            val intent = Intent(context, VideoDetailActivity::class.java)
            intent.putExtra(Constants.FAVORITE_ITEM_KEY, resultList)
            startActivity(intent)

            if (Build.VERSION.SDK_INT >= 34) {
                requireActivity().overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, android.R.anim.fade_in, android.R.anim.fade_out)
            }else{
                requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

        })
        //viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        binding.chipGroup.setOnCheckedStateChangeListener { chipGroup, ints ->
            val selectChip = chipGroup.checkedChipId
            when (selectChip) {
                R.id.fist_type -> {
                    chipGroupType(ChipType.FIRST)
                }
                R.id.second_type -> {
                    chipGroupType(ChipType.SECOND)
                }
                R.id.three_type -> {
                    chipGroupType(ChipType.THIRD)
                }
                R.id.fourth_type -> {
                    chipGroupType(ChipType.FOURTH)
                }
                R.id.fifth_type -> {
                    chipGroupType(ChipType.FIFTH)
                }
                R.id.sixth_type -> {
                    chipGroupType(ChipType.SIXTH)
                }
            }
        }

        binding.recyclerview.setOnScrollChangeListener { view, x, y, ox, oy ->
            if(y > oy) {
                val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 300 }
                binding.floatButton.startAnimation(fadeIn)
                binding.floatButton.isVisible = true
            }

            if(y <= oy) {
                val fadeIn = AlphaAnimation(1f, 0f).apply { duration = 300 }
                binding.floatButton.startAnimation(fadeIn)
                binding.floatButton.isVisible = false
            }
        }

        binding.floatButton.setOnClickListener {
            binding.recyclerview.scrollToPosition(0)
        }
    }

    private fun chipGroupType(type: ChipType) {
        if(binding.searchEditText.text.isEmpty()) {
            binding.recyclerview.isVisible = false
            binding.emptyMessage.isVisible = true
            //viewModel.getSearch(query = "", "")
        } else {
            binding.recyclerview.isVisible = true
            binding.emptyMessage.isVisible = false



            when(type) {
                ChipType.FIRST -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "0")
                }
                ChipType.SECOND -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "30")
                }
                ChipType.THIRD -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "20")
                }
                ChipType.FOURTH -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "17")
                }
                ChipType.FIFTH -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "19")
                }
                ChipType.SIXTH -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "25")
                }
            }
        }
        viewModel.search.observe(viewLifecycleOwner) {
            adapter.submitList(it.items)
            binding.recyclerview.adapter = adapter
        }
    }
}