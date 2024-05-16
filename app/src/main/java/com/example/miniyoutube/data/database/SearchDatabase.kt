package com.example.miniyoutube.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.miniyoutube.data.model.local.SnippetEntity


@Database(entities = [SnippetEntity::class], version = 2)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun snippetDao(): SnippetDao
}