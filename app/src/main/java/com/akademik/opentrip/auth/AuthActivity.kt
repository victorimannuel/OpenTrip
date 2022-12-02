package com.akademik.opentrip.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.akademik.opentrip.databinding.ActivityAuthBinding
import com.akademik.opentrip.helper.ViewPagerAdapter2
import com.google.android.material.tabs.TabLayout

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var fragments: ArrayList<Fragment>
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        fragments = arrayListOf(
            LoginFragment(),
            RegisterFragment()
        )

        //configuring tablayout and viewpager
        with(binding) {
            tabsAuth.addTab(tabsAuth.newTab().setText("Masuk"))
            tabsAuth.addTab(tabsAuth.newTab().setText("Daftar"))
            tabsAuth.tabGravity = TabLayout.GRAVITY_FILL
        }
        val adapter = ViewPagerAdapter2(fragments, supportFragmentManager)
        binding.viewPagerAuth.adapter = adapter
        binding.viewPagerAuth.setCurrentItem(0, true)
        binding.viewPagerAuth.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabsAuth))
        binding.tabsAuth.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPagerAuth.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}
