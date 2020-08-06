package com.tsndongo.appspaneltest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tsndongo.appspaneltest.R
import com.tsndongo.appspaneltest.fragment.EventsFragment
import com.tsndongo.appspaneltest.fragment.InfoFragment
import com.tsndongo.appspaneltest.fragment.SubscriptionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: TabPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pagerAdapter = TabPagerAdapter(this)
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = getTabText(position)
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    private fun getTabText(position: Int): String {
        return when (position) {
            1 -> resources.getString(R.string.tab_info_title)
            2 -> resources.getString(R.string.tab_subscription_title)
            else -> resources.getString(R.string.tab_events_title)
        }
    }
}
    class TabPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> InfoFragment.newInstance()
                2 -> SubscriptionFragment.newInstance()
                else -> EventsFragment.newInstance()
            }
        }

    }


