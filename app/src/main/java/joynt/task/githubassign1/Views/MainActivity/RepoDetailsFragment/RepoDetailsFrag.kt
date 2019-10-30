package joynt.task.githubassign1.Views.MainActivity.RepoDetailsFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nasa.Base.BaseFragment
import joynt.task.githubassign1.DI.Network.NetworkState
import joynt.task.githubassign1.Models.Contributor
import joynt.task.githubassign1.Models.Repo

import joynt.task.githubassign1.R
import kotlinx.android.synthetic.main.repo_details_fragment.*
import kotlinx.android.synthetic.main.repo_details_fragment.recycler
import javax.inject.Inject

class RepoDetailsFrag : BaseFragment<RepoDetailsViewModel>() {

    @Inject
    internal lateinit var viewModel: RepoDetailsViewModel

    @Inject
    internal lateinit var adapter: RepoContributorDetailsAdapter
    override fun layoutRes(): Int {
        return R.layout.repo_details_fragment
    }

    override fun getViewModel(): RepoDetailsViewModel {
        return viewModel
    }

    companion object {
        fun newInstance(repo: Repo?, fromLocal: Boolean) :RepoDetailsFrag {
            val fragment= RepoDetailsFrag()
            val bundle=Bundle()
            bundle.putSerializable("repo",repo)
            bundle.putBoolean("local",fromLocal)
            fragment.arguments =bundle
            return fragment
        }
    }

    var repo: Repo? = null
    var fromLocal: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repo=arguments?.getSerializable("repo") as Repo

        if (arguments?.containsKey("local")!!)
            fromLocal= arguments?.getBoolean("local",false)!!

        name.text = "Name : ${repo?.name}"
        desc.text = "Discrioption : ${repo?.description}"
        url.text = "Repository Url : ${repo?.html_url}"
        owner.text = "Owner : ${repo?.owner?.login}"

        val layoutManager = LinearLayoutManager(activity)
        recycler.layoutManager=layoutManager
        recycler.adapter=adapter

        viewModel.getContributors(repo!!,fromLocal)

        setObservers()
    }

    private fun setObservers() {
        viewModel.liveData?.observe(this,
            Observer<List<Contributor>> {
                if(it!=null)
                    adapter.submitList(it)
            })
        viewModel.networkState?.observe(this,
            Observer<NetworkState> {
                if (it.status!=NetworkState.SUCESS)
                    Toast.makeText(activity, "Unable To load Contributors...", Toast.LENGTH_SHORT).show()

            })
    }

}
