package changhwan.experiment.sopthomework.ui.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import changhwan.experiment.sopthomework.ui.view.home.adapter.HomeActivityViewPagerAdapter
import changhwan.experiment.sopthomework.ui.view.home.topfragment.HomeFragment
import changhwan.experiment.sopthomework.ui.view.profile.ProfileFragment
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.ActivityHomeBinding
import changhwan.experiment.sopthomework.ui.view.camera.CameraFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewPagerAdapter: HomeActivityViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initAdapter()
        initBottomNavigation()
    }

    private fun initAdapter() {
        val fragmentlist = listOf(ProfileFragment(), HomeFragment(), CameraFragment())

        homeViewPagerAdapter = HomeActivityViewPagerAdapter(this)
        homeViewPagerAdapter.homeActivityFragments.addAll(fragmentlist)

        binding.vpHome.adapter = homeViewPagerAdapter

    }

    private fun initBottomNavigation() {
        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnHome.menu.getItem(position).isChecked = true
            }
        })

        binding.bnHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_profile -> {
                    binding.vpHome.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpHome.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpHome.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }

            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}