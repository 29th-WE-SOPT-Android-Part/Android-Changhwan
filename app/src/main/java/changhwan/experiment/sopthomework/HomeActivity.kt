package changhwan.experiment.sopthomework

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import changhwan.experiment.sopthomework.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val introduce = Introduce(
            "이창환",
            26,
            "외워야겠다...",
            "현재시각 새벽 2시에 푸라닭을 결국 먹고있는 저는 돼지입니다 제발 살은 안쪘으면 좋겠네요",
            R.drawable.pig
        )

        binding.introduce = introduce

        binding.homeToGit.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/2chang5"))
            startActivity(intent)
        }

    }
}