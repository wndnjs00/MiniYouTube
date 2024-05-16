package com.example.miniyoutube.ui.myvideo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniyoutube.data.database.StorageDao
import com.example.miniyoutube.data.model.local.StorageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyVideoViewModel @Inject constructor(
    private val snippetDao: StorageDao
): ViewModel() {

    val snippetEntities: LiveData<List<StorageEntity>> = snippetDao.getAll()

    fun deleteSnippetEntity(videoId: String){
        viewModelScope.launch {
            snippetDao.deleteSnippet(videoId)
        }
    }
}