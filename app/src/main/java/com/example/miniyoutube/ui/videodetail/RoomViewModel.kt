package com.example.miniyoutube.ui.videodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniyoutube.data.database.SnippetDao
import com.example.miniyoutube.data.model.local.SnippetEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val snippetDao : SnippetDao) : ViewModel() {

    private val _getLikeData = MutableLiveData<List<SnippetEntity>>()
    val getLikeData : LiveData<List<SnippetEntity>> = _getLikeData


    fun saveLikeData(snippetEntity: SnippetEntity) = viewModelScope.launch {
        snippetDao.insert(snippetEntity)
    }


}