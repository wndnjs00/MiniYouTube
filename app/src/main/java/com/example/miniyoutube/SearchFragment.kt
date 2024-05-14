package com.example.miniyoutube

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.miniyoutube.SearchPackage.SearchViewModel
import com.example.miniyoutube.adapter.SearchAdapter
import com.example.miniyoutube.chipgroup.ChipType
import com.example.miniyoutube.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding : FragmentSearchBinding? = null
    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel: SearchViewModel
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
        bindViews()
    }


    private fun initViews() {
        adapter = SearchAdapter()
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

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
            }
        }
    }

    private fun bindViews() {

    }

    private fun chipGroupType(type: ChipType) {
        if(binding.searchEditText.text.isEmpty()) {
            viewModel.getSearch(query = "", "")
        } else {
            when(type) {
                ChipType.FIRST -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "영화")
                }
                ChipType.SECOND -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "키즈")
                }
                ChipType.THIRD -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "스포츠")
                }
                ChipType.FOURTH -> {
                    viewModel.getSearch(query = binding.searchEditText.text.toString(), "성인")
                }
            }
        }

        viewModel.search.observe(requireActivity(), Observer {
            adapter.submitList(
                listOf(it.body()?.items?.get(1)?.snippet)
            )
            binding.recyclerview.adapter = adapter
        })
    }
}