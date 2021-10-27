package changhwan.experiment.sopthomework

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import changhwan.experiment.sopthomework.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        val introduce = Introduce(
            liveName = MutableLiveData<String>().apply { value = "이창환" },
            LiveAge = MutableLiveData<Int>().apply { value = 26 },
            liveMbti = MutableLiveData<String>().apply { value = "외워야겠다 ..." },
            liveIntroduction = MutableLiveData<String>().apply { value = "현재시각 새벽 2시에 푸라닭을 결국 먹고있는 저는 돼지입니다 제발 살은 안쪘으면 좋겠네요" },
            resorce = R.drawable.pig
        )

        binding.introduce = introduce
        binding.lifecycleOwner = this

        binding.homeToGit.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/2chang5"))
            startActivity(intent)
        }

        siteFragment()

    }

    private fun siteFragment(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()


        supportFragmentManager.beginTransaction().add(R.id.fragmentFrame,followerFragment).commit()

        binding.followerButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame,followerFragment).commit()

        }

        binding.repoButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentFrame,repositoryFragment).commit()

        }

    }
}