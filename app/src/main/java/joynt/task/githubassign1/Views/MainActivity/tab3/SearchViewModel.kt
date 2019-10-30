package joynt.task.githubassign1.Views.MainActivity.tab3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.nasatask.DI.Network.ApiService
import com.app.nasatask.DI.database.GitRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import joynt.task.githubassign1.DI.Network.NetworkState
import joynt.task.githubassign1.Models.Repo
import retrofit2.Response
import javax.inject.Inject

class SearchViewModel @Inject constructor( private val service: ApiService, private val db: GitRepo) : ViewModel() {

     var liveData: MutableLiveData<List<Repo>>? = MutableLiveData()
     var networkState: MutableLiveData<NetworkState>? = MutableLiveData()



    fun searchUser(username: String, save: Boolean) {
        service.searchUserRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<List<Repo>>>() {
                override fun onSuccess(repodata: Response<List<Repo>>) {
                    if (repodata.isSuccessful)
                    {
                        liveData?.postValue(repodata.body())
                        networkState?.postValue(NetworkState(NetworkState.SUCESS, "Sucess"))


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
