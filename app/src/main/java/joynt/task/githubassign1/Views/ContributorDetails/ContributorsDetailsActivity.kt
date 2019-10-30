package joynt.task.githubassign1.Views.ContributorDetails

import android.os.Bundle
import androidx.lifecycle.Observer
import com.app.nasa.Base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import joynt.task.githubassign1.R
import joynt.task.githubassign1.Views.MainActivity.ContributorViewModel
import joynt.task.githubassign1.Views.MainActivity.tab3.SearchFragment
import kotlinx.android.synthetic.main.activity_details_contributors.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import javax.inject.Inject



class ContributorsDetailsActivity : BaseActivity<ContributorViewModel>() {

    @Inject
    internal lateinit var viewModel: ContributorViewModel


    var contributorId : Int? =null
    var username : String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        contributorId =intent.getIntExtra("contributorId",0)

        viewModel.getContributorDetails(contributorId!!)

        observer()
    }

    private fun observer() {
        viewModel.contributor.observe(this, Observer {
            name.setText("User Name : ${it.login}")
            contributions.setText("Contributions : ${it.contributions}")
            url.setText("User Url : ${it.html_url}")
            Glide.with(this).load(it.avatar_url).diskCacheStrategy(DiskCacheStrategy.ALL).into(avatar)
            username=it.login

            addFragment()
        })


    }

    private fun addFragment() {
        val fragment = SearchFragment()
        var bundle =Bundle()
        bundle.putString("username",username)
        fragment.arguments=bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame, fragment)
        transaction.commit()
    }


    override fun layoutRes(): Int {
        return R.layout.activity_details_contributors
    }

    override fun getViewModel(): ContributorViewModel {
        return viewModel
    }

}
