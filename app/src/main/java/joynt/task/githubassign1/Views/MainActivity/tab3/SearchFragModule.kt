package joynt.task.githubassign1.Views.MainActivity.tab3

import dagger.Module
import dagger.Provides
import joynt.task.githubassign1.Views.MainActivity.tab3.adapter.SearchRecyclerAdapter

@Module
class SearchFragModule
{
    @Provides
    internal fun provideRepoRecyclerAdapter(): SearchRecyclerAdapter {
        return SearchRecyclerAdapter()
    }

}