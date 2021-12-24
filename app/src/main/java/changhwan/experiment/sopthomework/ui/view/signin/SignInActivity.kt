package changhwan.experiment.sopthomework.ui.view.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.HandlerCompat.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import changhwan.experiment.sopthomework.MainApplication
import changhwan.experiment.sopthomework.ui.view.home.HomeActivity
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.data.local.SoptDatabase
import changhwan.experiment.sopthomework.data.local.SoptEntity
import changhwan.experiment.sopthomework.databinding.ActivitySignInBinding
import changhwan.experiment.sopthomework.ui.view.signup.SignUpActivity
import changhwan.experiment.sopthomework.ui.viewmodel.SignViewModel
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import org.koin.ext.getScopeName

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private val signInViewModel: SignViewModel by inject()
    val signInEmail = MutableLiveData<String>()
    val signInPassword = MutableLiveData<String>()
    private var data: List<SoptEntity>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.signInData = this
        binding.lifecycleOwner = this

        startLogin()
        startSignUp()
        observeSuccess()
        autoLogin()


    }

    private fun startLogin() {
        binding.loginButton.setOnClickListener {
            signInViewModel.getEmail("")
            signInViewModel.getPassword("")
            signInEmail.value?.let { signInViewModel.getEmail(it) }
            signInPassword.value?.let { signInViewModel.getPassword(it) }
            signInViewModel.startSignIn()
            val db = SoptDatabase.getInstance(applicationContext)
            if (binding.cbAutoLogin.isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    db!!.soptDao().insert(SoptEntity(autoLogin = true))
                }
            }
        }
    }

    private fun observeSuccess() {
        signInViewModel.conSuccess.observe(this, Observer {
            if (signInViewModel.conSuccess.value == true) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "${signInViewModel.viewName.value}님 환영합니다", Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.inEditId.text.clear()
                binding.inEditPw.text.clear()
                Toast.makeText(this, "로그인실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun startSignUp() {
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResult.launch(intent)
        }

        getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                binding.inEditId.text.clear()
                binding.inEditId.text.append(it.data?.getStringExtra("Id"))
                binding.inEditPw.text.clear()
                binding.inEditPw.text.append(it.data?.getStringExtra("Pw"))
            }
        }
    }

    private fun autoLogin() {
        val db = SoptDatabase.getInstance(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            data = db!!.soptDao().getAll()
            data?.let {
                withContext(Dispatchers.Main){
                    if (data!![0].autoLogin) {
                        signInViewModel.getEmail("kimwy1997@gmail.com")
                        signInViewModel.getPassword("123456")
                        signInViewModel.startSignIn()
                        Toast.makeText(this@SignInActivity, "자동로그인 완료", Toast.LENGTH_SHORT).show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            finish()
                        }, 2000L)
                    }
                }
            }
        }
    }
}
