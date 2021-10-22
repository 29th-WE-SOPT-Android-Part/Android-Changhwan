package changhwan.experiment.sopthomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import changhwan.experiment.sopthomework.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpDone.setOnClickListener{
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


    }
}