package com.example.miniyoutube.ui.myvideo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
) : ViewModel() {

    private val _visibilityView = MutableLiveData<VisibilityView>()
    val visibilityView: LiveData<VisibilityView>
        get() = _visibilityView

    val storageEntities: LiveData<List<StorageEntity>> = snippetDao.getAll()


    fun deleteSnippetEntity(videoId: String) {
        viewModelScope.launch {
            snippetDao.deleteSnippet(videoId)
        }
    }

    fun checkStorageEntities() {
        if (storageEntities.value.isNullOrEmpty()) {
            _visibilityView.value = VisibilityView.EMPTYVIEW
        } else {
            _visibilityView.value = VisibilityView.RECYCLERVIEW
        }
    }

}