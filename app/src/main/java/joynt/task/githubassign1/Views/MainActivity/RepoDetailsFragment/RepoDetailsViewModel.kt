package joynt.task.githubassign1.Views.MainActivity.RepoDetailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.app.nasatask.DI.Network.ApiService
import com.app.nasatask.DI.database.GitRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import joynt.task.githubassign1.DI.Network.NetworkState
import joynt.task.githubassign1.Models.Contributor
import joynt.task.githubassign1.Models.Repo
import retrofit2.Response
import java.util.concurrent.Executors
import javax.inject.Inject

class RepoDetailsViewModel @Inject
constructor( private val service: ApiService,private val db: GitRepo): ViewModel() {

    var liveData: MutableLiveData<List<Contributor>>? = MutableLiveData()
    var networkState: MutableLiveData<NetworkState>? = MutableLiveData()
    var reqCounter : Int =0
    fun getContributors(repo: Repo, fromLocal: Boolean) {

        db.getContributors(repo.id!!).observeForever(object : Observer<List<Contributor>>{
            override fun onChanged(t: List<Contributor>?) {
                if (t!=null && t.size!=0)
                    liveData?.postValue(t)
                else if (reqCounter>0)
                    requestFromApi(repo)
            }
        })

        if (!fromLocal)
           requestFromApi(repo)
    }

    private fun requestFromApi(repo: Repo) {
        service.searchRepoContribtors(repo.owner?.login!!, repo.name!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<List<Contributor>>>() {
                override fun onSuccess(repodata: Response<List<Contributor>>) {
                    reqCounter++
                    if (repodata.isSuccessful)
                    {
                        //                        liveData?.postValue(repodata.body())
                        networkState?.postValue(NetworkState(NetworkState.SUCESS, "Success"))
                        for (contributor in repodata.body()!!)
                        {
                            contributor.repoID= repo.id!!
                        }

                        Executors.newSingleThreadExecutor().execute {
                            db.insertContributors(repodata.body()!!)
                        }

                    }
                    else
                    {
                        networkState?.postValue(NetworkState(NetworkState.FAILED, "Failed"))
                    }
                }

                override fun onError(e: Throwable) {
                    networkState?.postValue(NetworkState(NetworkState.FAILED, e.message!!))
                }
            })
    }
}
