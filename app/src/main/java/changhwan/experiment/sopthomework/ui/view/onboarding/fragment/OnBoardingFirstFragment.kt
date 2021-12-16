package changhwan.experiment.sopthomework.ui.view.onboarding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentOnBoardingFirstBinding
import org.koin.android.ext.android.bind


class OnBoardingFirstFragment : Fragment() {

    private var _binding: FragmentOnBoardingFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingFirstBinding.inflate(inflater, container, false)


        binding.btnOnBoardFirst.setOnClickListener{
            findNavController().navigate(R.id.action_onBoardingFirstFragment_to_onBoardingSecondFragment)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}