package joynt.task.githubassign1.Views.MainActivity.tab1.Repos

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.app.nasatask.DI.Network.ApiService
import com.app.nasatask.Views.MianActivity.DataRepos.LocalRepo
import joynt.task.githubassign1.Models.Repo
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import joynt.task.githubassign1.DI.Network.NetworkState




class OnlineDataSourceFactory(internal var localRepo: LocalRepo, internal var service: ApiService)
            : DataSource.Factory<Long, Repo>() {

    private val sourceLiveData = MutableLiveData<OnlineKeydDataSource>()
    private lateinit var source :OnlineKeydDataSource
    var networkErrors: MutableLiveData<NetworkState>? = null

    override fun create(): DataSource<Long, Repo> {

        source = OnlineKeydDataSource(service,localRepo)
        networkErrors = source.networkErrors
        sourceLiveData.postValue(source)
        return source
    }

    fun getItemLiveDataSource(): ItemKeyedDataSource<Long, Repo> {
        return source
    }
}