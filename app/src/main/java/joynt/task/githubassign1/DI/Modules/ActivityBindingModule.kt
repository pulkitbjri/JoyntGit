package com.app.nasatask.DI.Modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import joynt.task.githubassign1.Views.ContributorDetails.ContributorsDetailsActivity
import joynt.task.githubassign1.Views.MainActivity.MainActivity
import joynt.task.githubassign1.Views.MainActivity.ContributorModule
import joynt.task.githubassign1.Views.MainActivity.MainActivityModule

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ContributorModule::class])
    abstract fun contributesContributorsDetailsActivity(): ContributorsDetailsActivity
}