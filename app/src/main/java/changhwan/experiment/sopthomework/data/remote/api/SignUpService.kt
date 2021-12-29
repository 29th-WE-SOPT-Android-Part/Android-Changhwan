package changhwan.experiment.sopthomework.data.remote.api

import changhwan.experiment.sopthomework.data.remote.model.request.RequestSignUpData
import changhwan.experiment.sopthomework.data.remote.model.response.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("user/signup")
    fun postSignUp (
        @Body body: RequestSignUpData
    ):Call<ResponseSignUpData>

}