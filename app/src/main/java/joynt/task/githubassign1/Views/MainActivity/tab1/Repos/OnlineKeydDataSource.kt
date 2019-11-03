package joynt.task.githubassign1.Views.MainActivity.tab1.Repos

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.app.nasatask.DI.Network.ApiService
import com.app.nasatask.Views.MianActivity.DataRepos.LocalRepo
import com.app.nasatask.interfaces.InsertFinished
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import joynt.task.githubassign1.DI.Network.NetworkState
import joynt.task.githubassign1.Models.Repo
import retrofit2.Response

class OnlineKeydDataSource(private val service: ApiService, private val localRepo: LocalRepo): ItemKeyedDataSource<Long, Repo>() {
//    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Repo>) {
//
//    }
//
//    override fun loadInitial(
//        params: LoadInitialParams<Long>,
//        callback: LoadInitialCallback<Long, Repo>
//    ) {
//        requestAndSaveData(0,callback,null)
//    }
//
//    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Repo>) {
//        requestAndSaveData(params.key,null,callback)
//
//    }

    val networkErrors: MutableLiveData<NetworkState>? = null
        get() = field
    private var isRequestInProgress = false

    override fun getKey(item: Repo): Long {
        return item.id!!
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Repo>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Repo>
    ) {
        requestAndSaveData(0,callback,null)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Repo>) {
        requestAndSaveData(params.key,null,callback)

    }


    private fun requestAndSaveData(repoId: Long?,
                                   callbackinit: LoadInitialCallback<Repo>?,
                                   callbackafter: LoadCallback<Repo>?) {
        if (isRequestInProgress)
            return

        isRequestInProgress = true
        val id= repoId!!

        val callback = service.searchRepos(id)
        callback
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<List<Repo>>>() {
                override fun onSuccess(repodata: Response<List<Repo>>) {
                    if (repodata.isSuccessful)
                    {

                        if (callbackinit!=null){
                            callbackinit.onResult(repodata.body()!!)
                        }
                        else
                        {
                            callbackafter?.onResult(repodata.body()!!)
                        }

                        localRepo.insert(repodata.body()!!, object : InsertFinished {
                            override fun insertFinished() {
                                isRequestInProgress = false
                            }
                        })

                    }
                    else
                    {
                        networkErrors?.postValue(NetworkState(NetworkState.FAILED,"Failed"))
                        isRequestInProgress = false

                    }
                }

                override fun onError(e: Throwable) {
                    networkErrors?.postValue(NetworkState(NetworkState.FAILED, e.message!!))
                    isRequestInProgress = false
                }
            })
    }

}
