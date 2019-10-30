package joynt.task.githubassign1.Views.MainActivity.RepoDetailsFragment

import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import joynt.task.githubassign1.Models.Contributor
import joynt.task.githubassign1.Models.Repo
import joynt.task.githubassign1.R
import joynt.task.githubassign1.Views.ContributorDetails.ContributorsDetailsActivity
import joynt.task.githubassign1.Views.MainActivity.MainActivity

class RepoContributorDetailsAdapter :
    RecyclerView.Adapter<RepoContributorDetailsAdapter.ViewHolder>() {
    private var list: ArrayList<Contributor> = ArrayList()
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.contributor_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun submitList(it: List<Contributor>) {
        list.clear()
        list.addAll(it)
        notifyDataSetChanged()

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contributor: Contributor) {
            if (contributor == null) {

                name.text = resources.getString(R.string.loading)
            } else {
                showRepoData(contributor)

            }
        }

        private fun showRepoData(repo: Contributor) {
            name.setText(repo.login)
            Glide.with(imageView).load(repo.avatar_url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
        }

        private val name: TextView
        private val imageView: ImageView
        private val resources: Resources
        init {
            name = itemView.findViewById(R.id.repo_name)
            imageView = itemView.findViewById(R.id.imageView)
            resources = itemView.resources
            itemView.setOnClickListener{
                itemView.context.startActivity(Intent(itemView.context,ContributorsDetailsActivity::class.java)
                    .putExtra("contributorId",list.get(adapterPosition).id))
            }
        }
    }

}