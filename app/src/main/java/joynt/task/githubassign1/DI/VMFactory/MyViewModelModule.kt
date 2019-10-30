package com.app.nasatask.DI.VMFactory

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import joynt.task.githubassign1.Views.MainActivity.ContributorViewModel
import joynt.task.githubassign1.Views.MainActivity.MainActivityViewModel
import joynt.task.githubassign1.Views.MainActivity.RepoDetailsFragment.RepoDetailsViewModel
import joynt.task.githubassign1.Views.MainActivity.tab1.RepoViewModel
import joynt.task.githubassign1.Views.MainActivity.tab2.LocalViewModel
import joynt.task.githubassign1.Views.MainActivity.tab3.SearchViewModel

@Module
abstract class MyViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(myViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContributorViewModel::class)
    abstract fun bindContributorsViewModel(myViewModel: ContributorViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    abstract fun bindRepoViewModel(myViewModel: RepoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocalViewModel::class)
    abstract fun bindLocalViewModel(myViewModel: LocalViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(myViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailsViewModel::class)
    abstract fun bindRepoDetailsModel(myViewModel: RepoDetailsViewModel): ViewModel
}