package joynt.task.githubassign1.Views.MainActivity.tab1

import android.app.Dialog
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nasa.Base.BaseFragment
import joynt.task.githubassign1.DI.Network.NetworkState
import joynt.task.githubassign1.Models.Repo

import joynt.task.githubassign1.R
import joynt.task.githubassign1.Views.MainActivity.Adapters.ScreenSlidePagerAdapter
import joynt.task.githubassign1.Views.MainActivity.tab1.adapter.RepoDetailsPagerAdapter
import joynt.task.githubassign1.Views.MainActivity.tab1.adapter.RepoRecyclerAdapter
import joynt.task.githubassign1.interfaces.ClickEvent
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.repo_fragment.*
import javax.inject.Inject

class RepoFragment : BaseFragment<RepoViewModel>() , ClickEvent{


    @Inject
    internal lateinit var viewModel: RepoViewModel

    @Inject
    internal lateinit var adapter: RepoRecyclerAdapter

    companion object {
        fun newInstance() =
            RepoFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        adapter.clickEvent=this
        recycler.layoutManager=layoutManager
        recycler.adapter=adapter
        viewModel.getData()
        setObservers()
    }

    private fun setObservers() {
        viewModel.let {

            viewModel.repoPagedList?.observe(this,
                Observer<PagedList<Repo>> {
                    adapter.submitList(it)
                })
            viewModel.networkState?.observe(this,
                Observer<NetworkState> {

                    showEmptyList(adapter.currentList?.size!! > 0)
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                })
        }
    }


    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            recycler.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }

    }

    override fun getViewModel(): RepoViewModel {
        return viewModel
    }
    override fun layoutRes(): Int {
        return R.layout.repo_fragment
    }
    override fun onClick(position: Int) {
        openDialog(position)
    }



    private fun openDialog(position: Int) {
        val displayRectangle = Rect()
        val window = activity?.window
        window?.decorView?.getWindowVisibleDisplayFrame(displayRectangle)

        val dialog = Dialog(context!!, android.R.style.Theme_Light)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val v= LayoutInflater.from(context).inflate(R.layout.dialog,null,false)
        v.setMinimumWidth((displayRectangle.width() * 1f).toInt());
        v.setMinimumHeight((displayRectangle.height() * 1f).toInt());
        dialog.setContentView(v)

        val pagerAdapter = RepoDetailsPagerAdapter(this)
        pagerAdapter.listItemCount=adapter.itemCount
        pagerAdapter.mainList= adapter.currentList!!
        v.pager.adapter=pagerAdapter

        v.pager.setCurrentItem(position)

        dialog.setOnDismissListener {
            recycler.scrollToPosition(v.pager.currentItem)
        }

        dialog.show()
    }

    override fun onLongClick(adapterPosition: Int) {

    }
}
