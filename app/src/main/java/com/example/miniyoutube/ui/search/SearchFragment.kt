package com.example.miniyoutube.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miniyoutube.ui.main.MainActivity
import com.example.miniyoutube.R
import com.example.miniyoutube.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding : FragmentSearchBinding? = null
    private lateinit var mainActivity: MainActivity

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
        //viewModel에있는 retrofit에서 타입값에 따라 받는값 변경
    }
}