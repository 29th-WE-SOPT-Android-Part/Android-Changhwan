package changhwan.experiment.sopthomework


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpViewModel : ViewModel() {

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
        _viewEmail.value = email
    }

    fun getName(name: String) {
        _viewName.value = name
    }

    fun getPassword(password: String) {
        _viewPassword.value = password
    }

    fun startSignUp() {
        val requestSignUpData = RequestSignUpData(
            email = _viewEmail.value!!,
            name = _viewName.value!!,
            password = _viewPassword.value!!
        )

        val call: Call<ResponseSignUpData> =
            ServiceCreator.signUpService.postSignUp(requestSignUpData)

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
}