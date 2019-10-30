package joynt.task.githubassign1.Views.MainActivity.tab1.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import joynt.task.githubassign1.Models.Repo
import joynt.task.githubassign1.R
import joynt.task.githubassign1.interfaces.ClickEvent

public class RepoRecyclerAdapter : PagedListAdapter<Repo, RepoRecyclerAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    lateinit var clickEvent: ClickEvent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.repo_view_item, parent, false)
        return ViewHolder(view)
    }

    public fun setListner( clickEvent: ClickEvent)
    {
        this.clickEvent=clickEvent
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
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
            if (clickEvent!=null){

                itemView.setOnClickListener{
                    clickEvent.onClick(adapterPosition)
                }
                itemView.setOnLongClickListener{
                    clickEvent.onLongClick(adapterPosition)
                    return@setOnLongClickListener true
                }
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id?.equals(newItem.id)!!
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }

}