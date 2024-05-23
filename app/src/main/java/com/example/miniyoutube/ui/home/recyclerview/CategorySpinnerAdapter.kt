package com.example.miniyoutube.ui.home.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.example.miniyoutube.databinding.ItemSpinnerHomeBinding

class CategorySpinnerAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val categoryList: List<String>
): ArrayAdapter<String>(context, resId, categoryList) {

    @SuppressLint("ViewHoler")//드롭다운하지 않은 상태의 스피너 항목 뷰
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.tvSpinnerItem.text = categoryList[position]

        return binding.root
    }

    @SuppressLint("ViewHoler")//드롭다운된 항목들 리스트의 뷰
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.tvSpinnerItem.text = categoryList[position]

        return binding.root
    }

    override fun getCount(): Int {
        return categoryList.size
    }
}