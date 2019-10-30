package joynt.task.githubassign1.Views.MainActivity

import android.os.Bundle
import com.app.nasa.Base.BaseActivity
import com.app.nasatask.DI.VMFactory.DaggerViewModelFactory
import joynt.task.githubassign1.Views.MainActivity.Adapters.ScreenSlidePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import com.google.android.material.tabs.TabLayout
import joynt.task.githubassign1.R
import joynt.task.githubassign1.Utils.TabLayoutMediator


class MainActivity : BaseActivity<MainActivityViewModel>() {


    lateinit var pagerAdapter: ScreenSlidePagerAdapter
    @Inject
    internal lateinit var viewModel: MainActivityViewModel
    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    override fun layoutRes(): Int {
        return joynt.task.githubassign1.R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        pagerAdapter = ScreenSlidePagerAdapter(this)
        pager.adapter = pagerAdapter
        TabLayoutMediator(
            tabs,
            pager,
            true,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    if (position==0)
                        tab.setText(getString(R.string.public_repos))
                    else if(position==1)
                        tab.setText(getString(R.string.saved))
                    else if(position==2)
                        tab.setText(getString(R.string.search))
                }

            }).attach()
    }

    override fun getViewModel(): MainActivityViewModel {
        return viewModel
    }

    override fun onBackPressed() {
        if (pager.currentItem == 0) {
            super.onBackPressed()
        } else {

            pager.setCurrentItem(pager.currentItem - 1, true)
        }
    }
}
