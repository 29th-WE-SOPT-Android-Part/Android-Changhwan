package changhwan.experiment.sopthomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import changhwan.experiment.sopthomework.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var  getResult : ActivityResultLauncher<Intent>
    private val signInViewModel by viewModels<SignViewModel>()
    val signInEmail = MutableLiveData<String>()
    val signInPassword = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in)
        binding.signInData = this
        binding.lifecycleOwner = this

        startLogin()
        startSignUp()
        observeSuccess()
    }

    private fun startLogin(){
        binding.loginButton.setOnClickListener {
            signInEmail.value?.let { signInViewModel.getEmail(it) }
            signInPassword.value?.let { signInViewModel.getPassword(it) }
            signInViewModel.startSignIn()

        }
    }

    private fun observeSuccess(){
        signInViewModel.conSuccess.observe(this, Observer {
            if (signInViewModel.conSuccess.value == true) {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "${signInViewModel.viewName.value}님 환영합니다", Toast.LENGTH_SHORT).show()
            } else {
                binding.inEditId.text.clear()
                binding.inEditPw.text.clear()
                Toast.makeText(this, "로그인실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun startSignUp(){
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            getResult.launch(intent)
        }

        getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) {
                binding.inEditId.text.clear()
                binding.inEditId.text.append(it.data?.getStringExtra("Id"))
                binding.inEditPw.text.clear()
                binding.inEditPw.text.append(it.data?.getStringExtra("Pw"))
            }
        }
    }



}