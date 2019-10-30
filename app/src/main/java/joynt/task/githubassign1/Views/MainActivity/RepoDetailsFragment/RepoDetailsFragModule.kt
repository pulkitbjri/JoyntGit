package joynt.task.githubassign1.Views.MainActivity.RepoDetailsFragment

import dagger.Module
import dagger.Provides
import joynt.task.githubassign1.Views.MainActivity.tab3.adapter.SearchRecyclerAdapter

@Module
class RepoDetailsFragModule
{
    @Provides
    internal fun provideRepoRecyclerAdapter(): RepoContributorDetailsAdapter {
        return RepoContributorDetailsAdapter()
    }


}