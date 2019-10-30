package com.app.nasatask.DI.database

import android.content.Context

import androidx.room.Room

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class RoomDBModule {

    @Singleton
    @Provides
    fun provideApodDatabase(context: Context): GitDatabase {
        return Room.databaseBuilder(
            context,
            GitDatabase::class.java, GitDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideApodDao(gitDatabase: GitDatabase): GitRepo {
        return gitDatabase.gitDao()
    }
}