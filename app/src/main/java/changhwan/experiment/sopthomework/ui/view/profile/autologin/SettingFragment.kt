package changhwan.experiment.sopthomework.ui.view.profile.autologin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import changhwan.experiment.sopthomework.MainApplication
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentSettingBinding
import changhwan.experiment.sopthomework.ui.view.profile.follower.FollowerFragment


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(layoutInflater,container,false)

        updataAutoLoginState()
        backSetting()
        actionAutoLoginStateChange()
        return binding.root
    }

    private fun updataAutoLoginState(){
        binding.cbAutoLoginState.isChecked = MainApplication.prefs.getBoolean("auto_login",false)
    }

    private fun actionAutoLoginStateChange(){
        binding.cbAutoLoginState.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                MainApplication.prefs.setBoolean("auto_login",true)
            }else{
                MainApplication.prefs.setBoolean("auto_login",false)
            }
        }
    }

    private fun backSetting(){
        binding.btnBack.setOnClickListener{
            binding.btnBack.setOnClickListener {
                val followerFragment = FollowerFragment()
                requireParentFragment().childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentFrame,followerFragment ).commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}