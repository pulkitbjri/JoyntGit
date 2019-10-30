package joynt.task.githubassign1.Views.MainActivity.tab2

import android.app.Dialog
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nasa.Base.BaseFragment
import joynt.task.githubassign1.Models.Repo

import joynt.task.githubassign1.R
import joynt.task.githubassign1.Views.MainActivity.tab1.adapter.RepoDetailsPagerAdapter
import joynt.task.githubassign1.Views.MainActivity.tab1.adapter.RepoRecyclerAdapter
import joynt.task.githubassign1.interfaces.ClickEvent
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.repo_fragment.*
import javax.inject.Inject

class LocalFragment : BaseFragment<LocalViewModel>(), ClickEvent {


    @Inject
    internal lateinit var viewModel: LocalViewModel

    @Inject
    internal lateinit var adapter: RepoRecyclerAdapter

    override fun layoutRes(): Int {
        return R.layout.repo_fragment
    }

    override fun getViewModel(): LocalViewModel {
        return viewModel
    }

    companion object {
        fun newInstance() =
            LocalFragment()
    }
    private fun setObservers() {
        viewModel.repoPagedList?.observe(this,
            Observer<PagedList<Repo>> {
                showEmptyList(it.size == 0)
                adapter.submitList(it)
            })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recycler.layoutManager=layoutManager
        adapter.clickEvent=this
        recycler.adapter=adapter
        setObservers()
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            emptyList.setText(getString(R.string.nodata_to_show))
            recycler.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }

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
        pagerAdapter.fromLocal=true
        v.pager.adapter=pagerAdapter

        v.pager.setCurrentItem(position)

        dialog.setOnDismissListener {
            recycler.scrollToPosition(v.pager.currentItem)
        }

        dialog.show()
    }
    override fun onClick(position: Int) {
        openDialog(position)
    }

    override fun onLongClick(adapterPosition: Int) {
        openDeleteDialog(adapter.currentList?.get(adapterPosition))
    }

    private fun openDeleteDialog(repo: Repo?) {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Delete Repo from Local?")
        builder.setMessage("Are you sure you want to delete this repo ${repo?.name} ?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            viewModel.deleteRepo(repo)
        }
        builder.setNegativeButton("No"){dialogInterface, which -> }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}
