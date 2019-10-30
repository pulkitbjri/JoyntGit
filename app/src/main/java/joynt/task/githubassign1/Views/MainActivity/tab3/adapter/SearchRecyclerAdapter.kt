package joynt.task.githubassign1.Views.MainActivity.tab3.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import joynt.task.githubassign1.Models.Repo
import joynt.task.githubassign1.R

class SearchRecyclerAdapter :
    RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    private var list: ArrayList<Repo> = ArrayList()

    public fun setList(list: List<Repo>)
    {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.repo_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repo: Repo?) {
            if (repo == null) {

                name.text = resources.getString(R.string.loading)
                description.visibility = View.GONE

            } else {
                showRepoData(repo)

            }
        }

        private fun showRepoData(repo: Repo) {
            name.setText(repo.name)

            // if the description is missing, hide the TextView

            var descriptionVisibility = View.GONE
            if (repo.description != null) {
                description.setText(repo.description)
                descriptionVisibility = View.VISIBLE
            }
            description.visibility = descriptionVisibility


        }

        private val name: TextView
        private val description: TextView
        private val resources: Resources
        init {
            name = itemView.findViewById(R.id.repo_name)
            description = itemView.findViewById(R.id.repo_description)
            resources = itemView.resources
        }
    }

}