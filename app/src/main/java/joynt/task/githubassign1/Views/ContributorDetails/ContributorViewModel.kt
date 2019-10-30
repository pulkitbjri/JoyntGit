package joynt.task.githubassign1.Views.MainActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.app.nasatask.DI.Network.ApiService
import com.app.nasatask.DI.database.GitRepo
import joynt.task.githubassign1.Models.Contributor
import javax.inject.Inject

class ContributorViewModel @Inject
constructor(private val service: ApiService, private val db: GitRepo) : ViewModel()
{
    var contributor : MutableLiveData<Contributor> = MutableLiveData()

    fun getContributorDetails(contributorId: Int) {
        db.getContributorbyId(contributorId).observeForever {
            if (it!=null )
                contributor.postValue(it)
        }
    }

}