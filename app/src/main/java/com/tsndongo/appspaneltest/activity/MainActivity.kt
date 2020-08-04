package com.tsndongo.appspaneltest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.tsndongo.appspaneltest.R
import com.tsndongo.appspaneltest.fragment.ActualityFragment
import com.tsndongo.appspaneltest.fragment.InfoFragment
import com.tsndongo.appspaneltest.fragment.SubscriptionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: TabPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpTabs()
        //setSupportActionBar(toolbar)
        pagerAdapter = TabPagerAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    private fun setUpTabs(){
        tabs.addTab(tabs.newTab().setText(resources.getString(R.string.tab_actualities_title)))
        tabs.addTab(tabs.newTab().setText(resources.getString(R.string.tab_info_title)))
        tabs.addTab(tabs.newTab().setText(resources.getString(R.string.tab_subscription_title)))
        tabs.tabGravity = TabLayout.GRAVITY_FILL
    }
}
    class TabPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> ActualityFragment.newInstance()
                2 -> InfoFragment.newInstance()
                3 -> SubscriptionFragment.newInstance()
                else -> ActualityFragment.newInstance()
            }
        }

    }


