package changhwan.experiment.sopthomework.ui.view.profile.autologin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import changhwan.experiment.sopthomework.MainApplication
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.data.local.SoptDatabase
import changhwan.experiment.sopthomework.data.local.SoptEntity
import changhwan.experiment.sopthomework.databinding.FragmentSettingBinding
import changhwan.experiment.sopthomework.ui.view.profile.follower.FollowerFragment


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
//    private val db = context?.let { SoptDatabase.getInstance(it.applicationContext) }

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
        val db = context?.let { SoptDatabase.getInstance(it.applicationContext) }
        binding.cbAutoLoginState.isChecked = db!!.soptDao().getAll()[0].autoLogin

        //shared로 되었던것
//        binding.cbAutoLoginState.isChecked = MainApplication.prefs.getBoolean("auto_login",false)
    }

    private fun actionAutoLoginStateChange(){
        binding.cbAutoLoginState.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                //shared
//                MainApplication.prefs.setBoolean("auto_login",true)
                val db = context?.let { SoptDatabase.getInstance(it.applicationContext) }
                db!!.soptDao().insert(SoptEntity(autoLogin = true))
            }else{
                val db = context?.let { SoptDatabase.getInstance(it.applicationContext) }
                db!!.soptDao().insert(SoptEntity(autoLogin = false))
                //shared
//                MainApplication.prefs.setBoolean("auto_login",false)
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