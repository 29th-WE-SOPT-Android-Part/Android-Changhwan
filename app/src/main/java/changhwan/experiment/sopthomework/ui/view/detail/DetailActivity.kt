package changhwan.experiment.sopthomework.ui.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.ActivityDetailBinding
import kotlin.properties.Delegates

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var name: String
    private var src by Delegates.notNull<Int>()
    lateinit var detailIntroduce : MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.detail = this
        binding.lifecycleOwner = this

        name = intent.getStringExtra("name")!!
        src = intent.getIntExtra("src", R.drawable.pig)

        siteFragment()
        decideDetailIntroduction()

    }

    fun siteFragment(){
        val detailFragment = DetailFragment()
        var bundle = Bundle()
        bundle.putString("name",name)
        bundle.putInt("src",src)
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction().add(R.id.detailFragmentFrame,detailFragment).commit()
    }

    fun decideDetailIntroduction(){
        if (name == "문다빈"){
            detailIntroduce=MutableLiveData<String>().apply { value = "고향은 경상남도 합천이고,현재 24살이며 안드로이드 파트장을 맡고있음.. 안드 좋아. 안드 좋아. 안드 좋아.안드 좋아. 안드 좋아. 안드 좋아. 안드 좋... "}
        }else if (name == "장혜령"){
            detailIntroduce=MutableLiveData<String>().apply { value = "누군지 몰라요 최송합니다"}
        }else if (name == "김우영"){
            detailIntroduce=MutableLiveData<String>().apply { value = "어제 만나서 삼곱식당에서 곱창에 삼겹살을 같이먹었죠 아주 맛있었습니다 후후후"}
        }else if( name == "이호재"){
            detailIntroduce=MutableLiveData<String>().apply { value = "호재야 가죽조져야지 어딜자꾸 도망가니"}
        }else if (name == "이강민"){
            detailIntroduce=MutableLiveData<String>().apply { value = "아이폰 13 pro와 갤럭시 폴드를 동시에 소유한 핸드폰왕"}
        }

    }

}