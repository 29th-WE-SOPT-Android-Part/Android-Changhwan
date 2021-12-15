package changhwan.experiment.sopthomework.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentDetailBinding
import kotlin.properties.Delegates


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var name : String
    private var src by Delegates.notNull<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = arguments?.getString("name")!!
        src = arguments?.getInt("src", R.drawable.pig)!!
        binding.detailImage.setImageResource(src!!)
        binding.detailName.text = name
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}