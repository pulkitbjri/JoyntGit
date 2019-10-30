package com.app.nasatask.DI.Modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import joynt.task.githubassign1.Views.MainActivity.RepoDetailsFragment.RepoDetailsFrag
import joynt.task.githubassign1.Views.MainActivity.RepoDetailsFragment.RepoDetailsFragModule
import joynt.task.githubassign1.Views.MainActivity.tab1.RepoFragModule
import joynt.task.githubassign1.Views.MainActivity.tab1.RepoFragment
import joynt.task.githubassign1.Views.MainActivity.tab2.LocalFragModule
import joynt.task.githubassign1.Views.MainActivity.tab2.LocalFragment
import joynt.task.githubassign1.Views.MainActivity.tab3.SearchFragModule
import joynt.task.githubassign1.Views.MainActivity.tab3.SearchFragment

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [RepoFragModule::class])
    abstract fun contributesRepoFrag(): RepoFragment

    @ContributesAndroidInjector(modules = [LocalFragModule::class])
    abstract fun contributesLocalFrag(): LocalFragment

    @ContributesAndroidInjector(modules = [SearchFragModule::class])
    abstract fun contributesSearchFrag(): SearchFragment

    @ContributesAndroidInjector(modules = [RepoDetailsFragModule::class])
    abstract fun contributesRepoDetailsFrag(): RepoDetailsFrag
}