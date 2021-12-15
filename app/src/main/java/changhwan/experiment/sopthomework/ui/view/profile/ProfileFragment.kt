package changhwan.experiment.sopthomework.ui.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import changhwan.experiment.sopthomework.ui.view.profile.detail.ProfileDetailFragment
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.ui.view.profile.repository.RepositoryFragment
import changhwan.experiment.sopthomework.databinding.FragmentProfileBinding
import changhwan.experiment.sopthomework.ui.view.profile.follower.FollowerFragment


class ProfileFragment : Fragment() {

    private var _binding :FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)


        siteFragment()
        siteDetailProfileFragment()

        return binding.root
    }


    private fun siteFragment(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()


        childFragmentManager.beginTransaction().add(R.id.fragmentFrame,followerFragment).commit()
        binding.followerButton.isChecked = true

        binding.followerButton.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.fragmentFrame,followerFragment).commit()

        }

        binding.repoButton.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.fragmentFrame,repositoryFragment).commit()

        }

    }

    private fun siteDetailProfileFragment(){
        val profileDetailFragment = ProfileDetailFragment()

        childFragmentManager.beginTransaction().add(R.id.profile_fragment_container,profileDetailFragment).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}