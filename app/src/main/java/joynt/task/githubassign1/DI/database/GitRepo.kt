package com.app.nasatask.DI.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import joynt.task.githubassign1.Models.Contributor
import joynt.task.githubassign1.Models.Repo

@Dao
interface GitRepo{

    @Query("Select * from repo")
    fun getRepos(): DataSource.Factory<Int, Repo>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(apod: List<Repo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContributors(repos: List<Contributor>)


    @Query("Select * from contributor where repoID = :repoId")
    fun getContributors(repoId: Long) : LiveData<List<Contributor>>

    @Query("Select * from contributor where id = :id limit 1")
    fun getContributorbyId(id: Int) : LiveData<Contributor>

    @Query("Delete from repo where id = :id")
    fun deleteRepo(id: Long?)

}
