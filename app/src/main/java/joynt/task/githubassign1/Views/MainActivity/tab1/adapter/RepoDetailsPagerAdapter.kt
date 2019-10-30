package joynt.task.githubassign1.Views.MainActivity.tab1.adapter

import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.viewpager2.adapter.FragmentStateAdapter
import joynt.task.githubassign1.Models.Repo
import joynt.task.githubassign1.Views.MainActivity.RepoDetailsFragment.RepoDetailsFrag

class RepoDetailsPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    var fromLocal: Boolean =false
    lateinit var mainList: PagedList<Repo>
    var listItemCount: Int=0

    override fun createFragment(position: Int): Fragment {

        return RepoDetailsFrag.newInstance(mainList.get(position),fromLocal)

    }

    override fun getItemCount(): Int = listItemCount


}