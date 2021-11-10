package changhwan.experiment.sopthomework

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val  SOPT_BASE_URL= "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val SoptRetrofit :Retrofit = Retrofit.Builder()
        .baseUrl(SOPT_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpService :SignUpService = SoptRetrofit.create(SignUpService::class.java)
    val signInService :SignInService = SoptRetrofit.create(SignInService::class.java)


    private const val  GITHUB_BASE_URL="https://api.github.com/"

    private  val GitHubRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val gitHubService :GitHubService = GitHubRetrofit.create(GitHubService::class.java)
}