package com.example.miniyoutube.ui.myvideo

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.miniyoutube.ui.videodetail.VideoDetailActivity
import com.example.miniyoutube.databinding.FragmentMyVideoBinding
import com.example.miniyoutube.ui.model.FavoriteItem
import com.example.miniyoutube.ui.model.toItem
import com.example.miniyoutube.ui.myvideo.recyclerview.MyVideoAdapter
import com.example.miniyoutube.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyVideoFragment : Fragment() {

    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

    private val myVideoAdapter: MyVideoAdapter by lazy {
        MyVideoAdapter(
            onMyVideoClick = ::goVideoDetail,
            onMyVideoLongClick = ::deleteSnippetEntity
        )
    }

    private val myVideoViewModel: MyVideoViewModel by viewModels()

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
        setupObserve()
    }

    private fun setupObserve() {
        myVideoViewModel.storageEntities.observe(viewLifecycleOwner) { storageEntityList ->
            myVideoViewModel.checkStorageEntities()
            myVideoAdapter.submitList(storageEntityList.map {
                it.toItem()
            })
        }

        myVideoViewModel.visibilityView.observe(viewLifecycleOwner) {
            when (it) {
                VisibilityView.EMPTYVIEW -> {
                    binding.tvEmpty.visibility = View.VISIBLE
                    binding.rv.visibility = View.INVISIBLE
                }

                VisibilityView.RECYCLERVIEW -> {
                    binding.tvEmpty.visibility = View.INVISIBLE
                    binding.rv.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setSearchAdapter() {
        val myVideoManager =
            GridLayoutManager(requireActivity(), 2)
        binding.rv.apply {
            layoutManager = myVideoManager
            adapter = myVideoAdapter
        }
    }


    private fun goVideoDetail(favoriteItem: FavoriteItem) {
        startActivity(Intent(requireActivity(), VideoDetailActivity::class.java).apply {
            this.putExtra(Constants.FAVORITE_ITEM_KEY, favoriteItem)
        })

        if (Build.VERSION.SDK_INT >= 34) {
            requireActivity().overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, android.R.anim.fade_in, android.R.anim.fade_out)
        }else{
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    private fun deleteSnippetEntity(favoriteItme: FavoriteItem): Boolean {
        myVideoViewModel.deleteSnippetEntity(favoriteItme.videoId)
        return true
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}