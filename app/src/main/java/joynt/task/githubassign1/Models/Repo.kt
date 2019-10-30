package joynt.task.githubassign1.Models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
open class Repo(
    var archive_url: String?,
    var assignees_url: String?,
    var blobs_url: String?,
    var branches_url: String?,
    var collaborators_url: String?,
    var comments_url: String?,
    var commits_url: String?,
    var compare_url: String?,
    var contents_url: String?,
    var contributors_url: String?,
    var deployments_url: String?,
    var description: String?,
    var downloads_url: String?,
    var events_url: String?,
    var fork: Boolean,
    var forks_url: String?,
    var full_name: String?,
    var git_commits_url: String?,
    var git_refs_url: String?,
    var git_tags_url: String?,
    var hooks_url: String?,
    var html_url: String?,
    @PrimaryKey
    var id: Long?,
    var issue_comment_url: String?,
    var issue_events_url: String?,
    var issues_url: String?,
    var keys_url: String?,
    var labels_url: String?,
    var languages_url: String?,
    var merges_url: String?,
    var milestones_url: String?,
    var name: String?,
    var node_id: String?,
    var notifications_url: String?,
    @Embedded(prefix = "repo_")
    var owner: Owner?,
    @SerializedName("private")
    var isPrivate: Boolean?,
    var pulls_url: String?,
    var releases_url: String?,
    var stargazers_url: String?,
    var statuses_url: String?,
    var subscribers_url: String?,
    var subscription_url: String?,
    var tags_url: String?,
    var teams_url: String?,
    var trees_url: String?,
    var url: String?

) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Repo) return false

        if (archive_url != other.archive_url) return false
        if (assignees_url != other.assignees_url) return false
        if (blobs_url != other.blobs_url) return false
        if (branches_url != other.branches_url) return false
        if (collaborators_url != other.collaborators_url) return false
        if (comments_url != other.comments_url) return false
        if (commits_url != other.commits_url) return false
        if (compare_url != other.compare_url) return false
        if (contents_url != other.contents_url) return false
        if (contributors_url != other.contributors_url) return false
        if (deployments_url != other.deployments_url) return false
        if (description != other.description) return false
        if (downloads_url != other.downloads_url) return false
        if (events_url != other.events_url) return false
        if (fork != other.fork) return false
        if (forks_url != other.forks_url) return false
        if (full_name != other.full_name) return false
        if (git_commits_url != other.git_commits_url) return false
        if (git_refs_url != other.git_refs_url) return false
        if (git_tags_url != other.git_tags_url) return false
        if (hooks_url != other.hooks_url) return false
        if (html_url != other.html_url) return false
        if (id != other.id) return false
        if (issue_comment_url != other.issue_comment_url) return false
        if (issue_events_url != other.issue_events_url) return false
        if (issues_url != other.issues_url) return false
        if (keys_url != other.keys_url) return false
        if (labels_url != other.labels_url) return false
        if (languages_url != other.languages_url) return false
        if (merges_url != other.merges_url) return false
        if (milestones_url != other.milestones_url) return false
        if (name != other.name) return false
        if (node_id != other.node_id) return false
        if (notifications_url != other.notifications_url) return false
        if (owner != other.owner) return false
        if (isPrivate != other.isPrivate) return false
        if (pulls_url != other.pulls_url) return false
        if (releases_url != other.releases_url) return false
        if (stargazers_url != other.stargazers_url) return false
        if (statuses_url != other.statuses_url) return false
        if (subscribers_url != other.subscribers_url) return false
        if (subscription_url != other.subscription_url) return false
        if (tags_url != other.tags_url) return false
        if (teams_url != other.teams_url) return false
        if (trees_url != other.trees_url) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = archive_url?.hashCode() ?: 0
        result = 31 * result + (assignees_url?.hashCode() ?: 0)
        result = 31 * result + (blobs_url?.hashCode() ?: 0)
        result = 31 * result + (branches_url?.hashCode() ?: 0)
        result = 31 * result + (collaborators_url?.hashCode() ?: 0)
        result = 31 * result + (comments_url?.hashCode() ?: 0)
        result = 31 * result + (commits_url?.hashCode() ?: 0)
        result = 31 * result + (compare_url?.hashCode() ?: 0)
        result = 31 * result + (contents_url?.hashCode() ?: 0)
        result = 31 * result + (contributors_url?.hashCode() ?: 0)
        result = 31 * result + (deployments_url?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (downloads_url?.hashCode() ?: 0)
        result = 31 * result + (events_url?.hashCode() ?: 0)
        result = 31 * result + fork.hashCode()
        result = 31 * result + (forks_url?.hashCode() ?: 0)
        result = 31 * result + (full_name?.hashCode() ?: 0)
        result = 31 * result + (git_commits_url?.hashCode() ?: 0)
        result = 31 * result + (git_refs_url?.hashCode() ?: 0)
        result = 31 * result + (git_tags_url?.hashCode() ?: 0)
        result = 31 * result + (hooks_url?.hashCode() ?: 0)
        result = 31 * result + (html_url?.hashCode() ?: 0)
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (issue_comment_url?.hashCode() ?: 0)
        result = 31 * result + (issue_events_url?.hashCode() ?: 0)
        result = 31 * result + (issues_url?.hashCode() ?: 0)
        result = 31 * result + (keys_url?.hashCode() ?: 0)
        result = 31 * result + (labels_url?.hashCode() ?: 0)
        result = 31 * result + (languages_url?.hashCode() ?: 0)
        result = 31 * result + (merges_url?.hashCode() ?: 0)
        result = 31 * result + (milestones_url?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (node_id?.hashCode() ?: 0)
        result = 31 * result + (notifications_url?.hashCode() ?: 0)
        result = 31 * result + (owner?.hashCode() ?: 0)
        result = 31 * result + (isPrivate?.hashCode() ?: 0)
        result = 31 * result + (pulls_url?.hashCode() ?: 0)
        result = 31 * result + (releases_url?.hashCode() ?: 0)
        result = 31 * result + (stargazers_url?.hashCode() ?: 0)
        result = 31 * result + (statuses_url?.hashCode() ?: 0)
        result = 31 * result + (subscribers_url?.hashCode() ?: 0)
        result = 31 * result + (subscription_url?.hashCode() ?: 0)
        result = 31 * result + (tags_url?.hashCode() ?: 0)
        result = 31 * result + (teams_url?.hashCode() ?: 0)
        result = 31 * result + (trees_url?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }
}