package joynt.task.githubassign1.Views.MainActivity.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import joynt.task.githubassign1.Views.MainActivity.tab1.RepoFragment
import joynt.task.githubassign1.Views.MainActivity.tab2.LocalFragment
import joynt.task.githubassign1.Views.MainActivity.tab3.SearchFragment

public class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {


    override fun createFragment(position: Int): Fragment {
        if (position==0)
            return RepoFragment.newInstance()
        if (position==1)
            return LocalFragment.newInstance()
        else
            return SearchFragment.newInstance()

    }

    override fun getItemCount(): Int = 3


    }