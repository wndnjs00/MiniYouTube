package com.example.miniyoutube.data.di

import android.content.Context
import androidx.room.Room
import com.example.miniyoutube.data.database.FavoriteDatabase
import com.example.miniyoutube.data.database.SnippetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesMeryDatabase(
        @ApplicationContext context: Context,
    ): FavoriteDatabase = Room.databaseBuilder(
        context = context,
        klass = FavoriteDatabase::class.java,
        name = "Favorite-database"
    ).build()

    @Provides
    fun providesSearchDao(
        database: FavoriteDatabase,
    ): SnippetDao = database.snippetDao()
}