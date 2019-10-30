package com.app.nasatask.DI.Network

import io.reactivex.Single
import joynt.task.githubassign1.Models.Contributor
import joynt.task.githubassign1.Models.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("repositories")
    fun searchRepos(
        @Query("since") lastKey: Long
    ): Single<Response<List<Repo>>>

    @GET("users/{username}/repos")
    fun searchUserRepos(
        @Path("username") userName: String
    ): Single<Response<List<Repo>>>


    @GET("repos/{username}/{reponame}/contributors")
    fun searchRepoContribtors(
        @Path("username") userName: String,
        @Path("reponame") reponame: String
    ): Single<Response<List<Contributor>>>

}

