package changhwan.experiment.sopthomework.ui.view.onboarding.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentOnBoardingSecondBinding
import changhwan.experiment.sopthomework.databinding.FragmentOnBoardingThirdBinding
import changhwan.experiment.sopthomework.ui.view.signin.SignInActivity


class OnBoardingThirdFragment : Fragment() {

    private var _binding: FragmentOnBoardingThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingThirdBinding.inflate(inflater, container, false)



        binding.btnOnBoardThird.setOnClickListener{
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        setBackButton()

        return binding.root
    }

    private fun setBackButton(){
        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().navigate(R.id.action_pop_onBoardingThirdFragment_to_onBoardingFirstFragment)
        }
    }
}