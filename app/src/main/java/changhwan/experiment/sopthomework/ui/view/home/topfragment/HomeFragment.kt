package changhwan.experiment.sopthomework.ui.view.home.topfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import changhwan.experiment.sopthomework.ui.view.home.datailpart.adapter.HomeFragmentViewPagerAdapter
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentHomeBinding
import changhwan.experiment.sopthomework.ui.view.home.datailpart.follower.HomeFollowerFragment
import changhwan.experiment.sopthomework.ui.view.home.datailpart.following.HomeFollowingFragment
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeFragmentViewPagerAdapter: HomeFragmentViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter(){
        val fragmentlist = listOf(HomeFollowingFragment(), HomeFollowerFragment())

        homeFragmentViewPagerAdapter = HomeFragmentViewPagerAdapter(this)
        homeFragmentViewPagerAdapter.homeFragmentFragments.addAll(fragmentlist)

        binding.vpFragmentHome.adapter = homeFragmentViewPagerAdapter
    }

    private fun initTabLayout(){
        val tabLable = listOf("팔로잉","팔로워")

        TabLayoutMediator(binding.tlFragmentHome,binding.vpFragmentHome) { tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}