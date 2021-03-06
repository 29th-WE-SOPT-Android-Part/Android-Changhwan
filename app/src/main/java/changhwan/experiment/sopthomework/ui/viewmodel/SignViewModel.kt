package changhwan.experiment.sopthomework.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import changhwan.experiment.sopthomework.data.remote.api.SignInService
import changhwan.experiment.sopthomework.data.remote.api.SignUpService
import changhwan.experiment.sopthomework.data.remote.model.request.RequestSignInData
import changhwan.experiment.sopthomework.data.remote.model.request.RequestSignUpData
import changhwan.experiment.sopthomework.data.remote.model.response.ResponseSignUpData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignViewModel(private val signInService : SignInService, private val signUpService: SignUpService) : ViewModel() {


    private val _viewEmail = MutableLiveData<String>()
    private val _viewName = MutableLiveData<String>()
    private val _viewPassword = MutableLiveData<String>()
    private val _conSuccess = MutableLiveData<Boolean>()

    val viewEmail: LiveData<String>
        get() = _viewEmail
    val viewName: LiveData<String>
        get() = _viewName
    val viewPassword: LiveData<String>
        get() = _viewPassword
    val conSuccess: LiveData<Boolean>
        get() = _conSuccess


    fun getEmail(email: String) {
        _viewEmail.postValue(email)
    }

    fun getName(name: String) {
        _viewName.postValue(name)
    }

    fun getPassword(password: String) {
        _viewPassword.postValue(password)
    }

    fun startSignUp() {

        val requestSignUpData = RequestSignUpData(
            email = _viewEmail.value!!,
            name = _viewName.value!!,
            password = _viewPassword.value!!
        )

        val call: Call<ResponseSignUpData> =
           signUpService.postSignUp(requestSignUpData)

        call.enqueue(object : Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    if (data != null) {
                        _viewName.value = data.name
                        _viewEmail.value = data.email
                    }

                    _conSuccess.value = true

                } else {
                    _conSuccess.value = false
                }
            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                _conSuccess.value = false
            }
        })
    }


    fun startSignIn() {
        val requestSignInData = RequestSignInData(
            email = _viewEmail.value!!,
            password = _viewPassword.value!!
        )

//        val call : Call<ResponseSignInData> = ServiceCreator.signInService.postSignIn(requestSignInData)
//
//        call.enqueue(object : Callback<ResponseSignInData>{
//            override fun onResponse(
//                call: Call<ResponseSignInData>,
//                response: Response<ResponseSignInData>
//            ) {
//                val data = response.body()?.data
//
//                if(response.isSuccessful){
//                    if (data != null) {
//                        _viewName.value = data.name
//                    }
//                    _conSuccess.value = true
//                } else {
//                    _conSuccess.value = false
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
//                _conSuccess.value = false
//            }
//
//        })

        viewModelScope.launch {
            val response = signInService.postSignIn(requestSignInData)
            val data = response.body()?.data

            if (response.isSuccessful) {
                if (data != null) {
                    _viewName.value = data.name
                }
                _conSuccess.value = true
            } else {
                _conSuccess.value = false
            }
        }
    }
}