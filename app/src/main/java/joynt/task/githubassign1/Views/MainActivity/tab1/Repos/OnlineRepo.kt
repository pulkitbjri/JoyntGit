package com.app.nasatask.Views.MianActivity.DataRepos

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.nasatask.DI.Network.ApiService
import joynt.task.githubassign1.Models.RepoSearchResult
import joynt.task.githubassign1.Views.MainActivity.tab1.Repos.RepoBoundaryCallback

class OnlineRepo(internal var localRepo: LocalRepo, internal var service: ApiService) {

    fun Search(): RepoSearchResult {
        // Get data from the local cache
        val dataSourceFactory = localRepo.repos()

        val boundaryCallback =
            RepoBoundaryCallback(
                service,
                localRepo
            )

        val networkErrors = boundaryCallback.networkErrors

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(true)
            .build()
        //returns LiveData
        val data = LivePagedListBuilder(dataSourceFactory!!, config)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return RepoSearchResult(data, networkErrors,null)
    }
}
