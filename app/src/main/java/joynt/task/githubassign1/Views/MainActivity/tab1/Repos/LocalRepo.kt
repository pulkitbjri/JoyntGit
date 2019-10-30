package com.app.nasatask.Views.MianActivity.DataRepos

import androidx.paging.DataSource
import com.app.nasatask.DI.database.GitRepo
import com.app.nasatask.interfaces.InsertFinished
import joynt.task.githubassign1.Models.Repo
import java.util.concurrent.Executors

class LocalRepo(internal var repo: GitRepo) {

    internal fun repos (): DataSource.Factory<Int, Repo>?
    {
        return repo.getRepos()
    }

    internal fun insert(repos: List<Repo>, finished: InsertFinished) {
        Executors.newSingleThreadExecutor().execute {
            repo.insert(repos)
            finished.insertFinished()
        }
    }

}
