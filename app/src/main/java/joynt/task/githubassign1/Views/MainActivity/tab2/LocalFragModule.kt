package joynt.task.githubassign1.Views.MainActivity.tab2

import dagger.Module
import dagger.Provides
import joynt.task.githubassign1.Views.MainActivity.tab1.adapter.RepoRecyclerAdapter

@Module
class LocalFragModule
{
    @Provides
    internal fun provideRepoRecyclerAdapter(): RepoRecyclerAdapter {
        return RepoRecyclerAdapter()
    }

}