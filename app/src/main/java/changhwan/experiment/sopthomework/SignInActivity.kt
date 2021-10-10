package changhwan.experiment.sopthomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import changhwan.experiment.sopthomework.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var  getResult : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            if (binding.inEditId.text.toString() != "" && binding.inEditPw.text.toString() != "") {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "이창환님 환영합니다", Toast.LENGTH_SHORT).show()
            } else {
                binding.inEditId.text.clear()
                binding.inEditPw.text.clear()
                Toast.makeText(this, "로그인실패", Toast.LENGTH_SHORT).show()
            }
        }

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