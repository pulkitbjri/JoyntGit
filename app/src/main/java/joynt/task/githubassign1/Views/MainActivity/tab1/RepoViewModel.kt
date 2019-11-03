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
import androidx.paging.DataSource
import joynt.task.githubassign1.Views.MainActivity.tab1.Repos.OnlineDataSourceFactory
import androidx.paging.LivePagedListBuilder
import java.util.concurrent.Executors


class RepoViewModel @Inject
constructor(private val repo: GitRepo, private val service: ApiService): ViewModel() {
     var networkState: LiveData<NetworkState>? = null
     var repoPagedList: LiveData<PagedList<Repo>>? = null

    private var mostRecentDataSource: DataSource<Long, Repo>? = null

    init {
//            getData()
        }

    public fun getData() {

        val localRepo = LocalRepo(repo)
        val dataSourceFactory = OnlineDataSourceFactory(localRepo ,service)

        mostRecentDataSource = dataSourceFactory.create();


        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(true)
            .build()

        networkState = dataSourceFactory.networkErrors

        repoPagedList = LivePagedListBuilder(dataSourceFactory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()




//
//        val onlineRepo = OnlineRepo(localRepo, service)
//
//        val newsResult = onlineRepo.Search()
//
//        repoPagedList = newsResult.data
//
//        networkState = newsResult.networkErrors
    }
}
