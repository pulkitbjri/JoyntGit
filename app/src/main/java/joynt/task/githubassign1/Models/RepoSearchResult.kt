package joynt.task.githubassign1.Models


import androidx.lifecycle.LiveData
import androidx.paging.PagedList

import joynt.task.githubassign1.DI.Network.NetworkState

class RepoSearchResult(
    var data: LiveData<PagedList<Repo>>?,
    var networkErrors: LiveData<NetworkState>?,
    var listLiveData: LiveData<List<Repo>>?
)
