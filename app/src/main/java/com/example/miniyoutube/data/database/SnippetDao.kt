package com.example.miniyoutube.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.miniyoutube.data.model.local.SnippetEntity

@Dao
interface SnippetDao {

    @Query("SELECT * FROM snippet")
    fun getAll(): LiveData<List<SnippetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(snippetEntity: SnippetEntity)

    @Query("DELETE FROM snippet WHERE videoId = :videoId")
    suspend fun deleteSnippet(videoId: String)

//    OnConflictStrategy.ABORT	충돌이 발생할 경우 처리 중단
//    OnConflictStrategy.FAIL	충돌이 발생할 경우 실패처리
//    OnConflictStrategy.IGNORE	충돌이 발생할 경우 무시
//    OnConflictStrategy.REPLACE	충돌이 발생할 경우 덮어쓰기
//    OnConflictStrategy.ROLLBACK	충돌이 발생할 경우 이전으로 되돌리기
}