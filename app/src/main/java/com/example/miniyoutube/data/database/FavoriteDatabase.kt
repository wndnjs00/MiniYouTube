package com.example.miniyoutube.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.miniyoutube.data.model.local.StorageEntity


@Database(entities = [StorageEntity::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun storageDao(): StorageDao

}


