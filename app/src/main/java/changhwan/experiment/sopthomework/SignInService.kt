package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun postSignIn(
        @Body body: RequestSignInData
    ):Call<ResponseSignInData>
}