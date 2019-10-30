package joynt.task.githubassign1.Views.MainActivity.tab1

import dagger.Module
import dagger.Provides
import joynt.task.githubassign1.Views.MainActivity.tab1.adapter.RepoRecyclerAdapter

@Module
class RepoFragModule
{
    @Provides
    internal fun provideRepoRecyclerAdapter(): RepoRecyclerAdapter {
        return RepoRecyclerAdapter()
    }

}