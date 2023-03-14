package com.example.appstore.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appstore.databinding.FragmentViewPagerConatinerBinding
import com.example.appstore.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created by himphen on 21/5/16.
 */
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
        viewBinding.viewPager.adapter = adapter
        viewBinding.viewPager.isUserInputEnabled = false

        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager) { tab, position ->
            tab.customView = adapter.getTabView(position)
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
