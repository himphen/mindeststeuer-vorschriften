package com.example.appstore.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.appstore.databinding.FragmentViewPagerConatinerBinding
import com.example.appstore.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : BaseFragment<FragmentViewPagerConatinerBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentViewPagerConatinerBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewBinding = viewBinding!!
        val adapter = MainFragmentPagerAdapter(this)
        viewBinding.viewPager.apply {
            this.adapter = adapter
            isUserInputEnabled = false
            offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        }

        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager) { tab, position ->
            tab.customView = adapter.getTabView(position)
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
