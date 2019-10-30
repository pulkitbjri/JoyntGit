package joynt.task.githubassign1.Views.MainActivity.tab2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.nasatask.DI.database.GitRepo
import joynt.task.githubassign1.Models.Repo
import javax.inject.Inject

class LocalViewModel  @Inject
constructor(private val repo: GitRepo): ViewModel() {
    fun deleteRepo(rep: Repo?) {
        repo.deleteRepo(rep?.id)
    }

    var repoPagedList: LiveData<PagedList<Repo>>? = null

    init {
        val dataSourceFactory = repo.getRepos()
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(true)
            .build()
        //returns LiveData
        repoPagedList = LivePagedListBuilder(dataSourceFactory!!, config)
            .build()

    }

}
