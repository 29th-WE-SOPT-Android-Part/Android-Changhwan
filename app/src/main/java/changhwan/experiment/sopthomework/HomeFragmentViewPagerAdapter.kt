package changhwan.experiment.sopthomework

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeFragmentViewPagerAdapter(fragment :Fragment): FragmentStateAdapter(fragment) {

    val homeFragmentFragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int =homeFragmentFragments.size

    override fun createFragment(position: Int): Fragment =homeFragmentFragments[position]
}