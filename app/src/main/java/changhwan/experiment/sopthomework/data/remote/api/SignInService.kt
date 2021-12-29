package changhwan.experiment.sopthomework.data.remote.api

import changhwan.experiment.sopthomework.data.remote.model.request.RequestSignInData
import changhwan.experiment.sopthomework.data.remote.model.response.ResponseSignInData
import changhwan.experiment.sopthomework.data.remote.model.response.wrapper.ResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("user/login")
    suspend fun postSignIn(
        @Body body: RequestSignInData
    ):Response<ResponseWrapper<ResponseSignInData>>
}