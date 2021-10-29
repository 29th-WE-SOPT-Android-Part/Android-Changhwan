package changhwan.experiment.sopthomework

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeActivityViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    val homeActivityFragments = mutableListOf<Fragment>()


    override fun getItemCount(): Int = homeActivityFragments.size

    override fun createFragment(position: Int): Fragment = homeActivityFragments[position]
}