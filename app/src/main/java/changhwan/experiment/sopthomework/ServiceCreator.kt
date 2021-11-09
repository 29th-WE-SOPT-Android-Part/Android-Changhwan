package changhwan.experiment.sopthomework

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val  BASE_URL= "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit :Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpService :SignUpService = retrofit.create(SignUpService::class.java)
    val signInService :SignInService = retrofit.create(SignInService::class.java)
}