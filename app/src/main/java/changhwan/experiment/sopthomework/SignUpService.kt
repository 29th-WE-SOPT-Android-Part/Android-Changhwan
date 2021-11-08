package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService {
    @Headers("Content-Type: application/json")
    @POST("user/signup")
    fun postSignUp (
        @Body body: RequestSignUpData
    ):Call<ResponseSignUpData>

}