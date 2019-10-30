package com.app.nasatask.DI.database


import androidx.room.Database
import androidx.room.RoomDatabase
import joynt.task.githubassign1.Models.Contributor
import joynt.task.githubassign1.Models.Repo

@Database(entities = [Repo::class,Contributor::class], version = 1,exportSchema = false)
abstract class GitDatabase : RoomDatabase() {

    abstract fun gitDao(): GitRepo

    companion object {
        val DATABASE_NAME = "apod.db"
    }


}
