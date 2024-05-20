package com.example.miniyoutube.ui.videodetail

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
class DetailViewModel @Inject constructor(private val storageDao : StorageDao) : ViewModel() {

    private val _getLikeData = MutableLiveData<List<StorageEntity>>()
    val getLikeData : LiveData<List<StorageEntity>> = _getLikeData


    fun saveLikeData(storageEntity: StorageEntity) = viewModelScope.launch {
        storageDao.insert(storageEntity)
    }


    fun deleteLikeData(videoId: String) = viewModelScope.launch {
        storageDao.deleteSnippet(videoId)
    }


    fun isVideoLiked(videoId: String) : LiveData<Boolean>{
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch {
            // 좋아요가 클릭되었는지 확인
            result.postValue(storageDao.videoLiked(videoId) != null)
        }
        return result
    }


}