package joynt.task.githubassign1.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GithubSearchResponse {


    @SerializedName("items")
    @Expose
    val items: List<Repo>? = null


}
