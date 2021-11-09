package changhwan.experiment.sopthomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import changhwan.experiment.sopthomework.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private val signupViewModel by viewModels<SignViewModel>()
    val nameText = MutableLiveData<String>()
    val emailText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        binding.signUpData = this
        binding.lifecycleOwner = this



        binding.signUpDone.setOnClickListener{
            startSignIn()
            getSignUpData()
        }


    }


    private fun startSignIn() {
        if(binding.upEditId.text.toString() != "" && binding.upEditPw.text.toString() != "" && binding.upEditName.text.toString() != ""){
            val intent = Intent(this,SignInActivity::class.java).apply {
                this.putExtra("Id",binding.upEditId.text.toString())
                this.putExtra("Pw",binding.upEditPw.text.toString())
            }
            setResult(RESULT_OK,intent)
            finish()
        }else{
            Toast.makeText(this,"입력되지않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSignUpData(){
        nameText.value?.let { signupViewModel.getName(it) }
        emailText.value?.let { signupViewModel.getEmail(it) }
        passwordText.value?.let { signupViewModel.getPassword(it) }
        signupViewModel.startSignUp()

        signupViewModel.conSuccess.observe(this, Observer {
            if (signupViewModel.conSuccess.value == true){
                Toast.makeText(this@SignUpActivity, "${signupViewModel.viewEmail.value}님 안녕하세요", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this@SignUpActivity, "뭔지는 정확히 모르겠는데 통신이상하게됨", Toast.LENGTH_SHORT).show()
                //이메일 아무거나 형식 안맞춰서 보내도 200으로 돌아옴 문제있다.
            }
        })

    }

}