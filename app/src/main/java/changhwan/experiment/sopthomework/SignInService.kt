package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {
    @POST("user/login")
    suspend fun postSignIn(
        @Body body: RequestSignInData
    ):Response<ResponseSignInData>
}