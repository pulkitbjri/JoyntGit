package joynt.task.githubassign1.Views.MainActivity.tab1

import androidx.lifecycle.ViewModel
import com.app.nasatask.DI.Network.ApiService
import com.app.nasatask.DI.database.GitRepo
import javax.inject.Inject
import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import com.app.nasatask.Views.MianActivity.DataRepos.LocalRepo
import com.app.nasatask.Views.MianActivity.DataRepos.OnlineRepo
import joynt.task.githubassign1.DI.Network.NetworkState
import joynt.task.githubassign1.Models.Repo

class RepoViewModel @Inject
constructor(private val repo: GitRepo, private val service: ApiService): ViewModel() {
     var networkState: LiveData<NetworkState>? = null
     var repoPagedList: LiveData<PagedList<Repo>>? = null

        init {
            getData()
        }

    private fun getData() {
        val localRepo = LocalRepo(repo)

        val onlineRepo = OnlineRepo(localRepo, service)

        val newsResult = onlineRepo.Search()

        repoPagedList = newsResult.data

         networkState = newsResult.networkErrors
    }
}
