package changhwan.experiment.sopthomework.ui.view.profile.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import changhwan.experiment.sopthomework.ui.view.profile.data.Introduce
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentProfileDetailBinding
import changhwan.experiment.sopthomework.ui.view.profile.autologin.SettingFragment
import com.bumptech.glide.Glide


class ProfileDetailFragment : Fragment() {

    private var _binding: FragmentProfileDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile_detail, container, false
        )

        val introduce = Introduce(
            liveName = MutableLiveData<String>().apply { value = "Dabin Moon" },
            liveID = MutableLiveData<String>().apply { value = "mdb1217" },
            liveIntroduction = MutableLiveData<String>().apply { value = "|king받는 개발자 문다빈" },
            resorce = 55//임시값
        )

        binding.profileDetail = introduce
        binding.lifecycleOwner = this

        initImg()
        settingAutoLogin()

        return binding.root
    }


    private fun settingAutoLogin() {
        binding.setUpIcon.setOnClickListener {
            val settingFragment = SettingFragment()
            requireParentFragment().childFragmentManager.beginTransaction()
                .replace(R.id.fragmentFrame, settingFragment).commit()
        }
    }

    private fun initImg() {
        Glide.with(this)
            .load(R.drawable.minion)
            .circleCrop()
            .into(binding.profileDetailImg)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}