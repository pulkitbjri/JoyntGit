package joynt.task.githubassign1.Views.MainActivity.tab1.Repos

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.app.nasatask.DI.Network.ApiService
import com.app.nasatask.Views.MianActivity.DataRepos.LocalRepo
import com.app.nasatask.interfaces.InsertFinished
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import joynt.task.githubassign1.DI.Network.NetworkState
import joynt.task.githubassign1.Models.Repo
import retrofit2.Response

public class RepoBoundaryCallback(private val service: ApiService, private val localRepo: LocalRepo) : PagedList.BoundaryCallback<Repo>() {
    val networkErrors: MutableLiveData<NetworkState>? = null
        get() = field
    private var isRequestInProgress = false
    private var lastRequestedPage = 0

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        requestAndSaveData(null)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Repo) {
        super.onItemAtEndLoaded(itemAtEnd)
        requestAndSaveData(itemAtEnd)

    }

    override fun onItemAtFrontLoaded(itemAtFront: Repo) {
        super.onItemAtFrontLoaded(itemAtFront)
    }

    private fun requestAndSaveData( repo: Repo?) {
        if (isRequestInProgress)
            return

        isRequestInProgress = true
        val id : Long
        if (repo==null)
            id=0
        else
            id= repo.id!!
        val callback = service.searchRepos(id)
        callback
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<List<Repo>>>() {
                override fun onSuccess(repodata: Response<List<Repo>>) {
                    if (repodata.isSuccessful)
                    {
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
