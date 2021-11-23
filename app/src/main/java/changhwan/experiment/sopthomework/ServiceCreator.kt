package changhwan.experiment.sopthomework

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

//    private val headerInterceptor = Interceptor{
//        val request = it.request()
//            .newBuilder()
//            .addHeader("Content-Type","application/json")
//            .build()
//        return@Interceptor it.proceed(request)
//    }
//
//    val client: OkHttpClient = OkHttpClient.Builder()
//        .addInterceptor(headerInterceptor)
//        .build()
//
//    private const val  SOPT_BASE_URL= "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
//
//    private val SoptRetrofit :Retrofit = Retrofit.Builder()
//        .baseUrl(SOPT_BASE_URL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val signUpService :SignUpService = SoptRetrofit.create(SignUpService::class.java)
//    val signInService :SignInService = SoptRetrofit.create(SignInService::class.java)


    private const val  GITHUB_BASE_URL="https://api.github.com/"

    private  val GitHubRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val gitHubService :GitHubService = GitHubRetrofit.create(GitHubService::class.java)
}