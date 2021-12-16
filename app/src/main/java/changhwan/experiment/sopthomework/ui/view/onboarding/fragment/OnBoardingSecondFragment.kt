package changhwan.experiment.sopthomework.ui.view.onboarding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentOnBoardingFirstBinding
import changhwan.experiment.sopthomework.databinding.FragmentOnBoardingSecondBinding


class OnBoardingSecondFragment : Fragment() {

    private var _binding: FragmentOnBoardingSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingSecondBinding.inflate(inflater, container, false)


        binding.btnOnBoardSecond.setOnClickListener{
            findNavController().navigate(R.id.action_onBoardingSecondFragment_to_onBoardingThirdFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}