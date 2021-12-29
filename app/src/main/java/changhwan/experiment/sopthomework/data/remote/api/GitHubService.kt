package changhwan.experiment.sopthomework.data.remote.api

import changhwan.experiment.sopthomework.data.remote.model.response.ResponseGitHubFollowerData
import changhwan.experiment.sopthomework.data.remote.model.response.ResponseGithubUserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{userId}/followers")
    fun getGitHubFollowers(
        @Path("userId") userId:String
    ): Call<List<ResponseGitHubFollowerData>>

    @GET("users/{userId}")
    fun getGitHubUsers(
        @Path("userId") userId:String
    ): Call<ResponseGithubUserData>
}