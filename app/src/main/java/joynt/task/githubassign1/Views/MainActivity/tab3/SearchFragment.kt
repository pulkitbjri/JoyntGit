package joynt.task.githubassign1.Views.MainActivity.tab3

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nasa.Base.BaseFragment
import kotlinx.android.synthetic.main.repo_fragment.*
import javax.inject.Inject
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import joynt.task.githubassign1.DI.Network.NetworkState
import joynt.task.githubassign1.Models.Repo
import joynt.task.githubassign1.R
import joynt.task.githubassign1.Views.MainActivity.tab3.adapter.SearchRecyclerAdapter
import java.util.concurrent.TimeUnit


class SearchFragment : BaseFragment<SearchViewModel>() {
    @Inject
    internal lateinit var adapter: SearchRecyclerAdapter
    @Inject
    internal lateinit var viewModel: SearchViewModel

    override fun layoutRes(): Int {
        return joynt.task.githubassign1.R.layout.repo_fragment
    }

    override fun getViewModel(): SearchViewModel {
        return viewModel
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
    var username:String =""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recycler.layoutManager=layoutManager
        recycler.adapter=adapter
        setObservers()

        emptyList.visibility = View.VISIBLE
        if (arguments!=null && arguments?.containsKey("username")!!)
        {
            username= arguments?.getString("username")!!
            viewModel.searchUser(username,true)
            emptyList.setText(getString(R.string.loading))

        }
        else
        {
            emptyList.setText(getString(R.string.search_username))

            setHasOptionsMenu(true)
        }
    }

    private var searchView: SearchView? = null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(joynt.task.githubassign1.R.menu.search, menu);

        val searchItem = menu.findItem(joynt.task.githubassign1.R.id.search)
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView

            fromView().debounce(900, TimeUnit.MILLISECONDS)
                //                .filter(s -> !s.isEmpty())
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<String>() {
                    override fun onNext(o: String) {
                        viewModel.searchUser(o,false)
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
        }
        super.onCreateOptionsMenu(menu, inflater);

    }

    fun fromView(): Observable<String> {

        val subject = PublishSubject.create<String>()

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                searchView?.clearFocus()
                subject.onComplete()
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)
                return true
            }
        })

        return subject
    }
    private fun setObservers() {
        viewModel.liveData?.observe(this,
            Observer<List<Repo>> {
                showEmptyList(it.size == 0)
                adapter.setList(it)
            })
        viewModel.networkState?.observe(this,
            Observer<NetworkState> {
                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                if (it.status!=NetworkState.SUCESS)
                {
                    adapter.clear()
                    showEmptyList(true)
                }
            })
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
}
