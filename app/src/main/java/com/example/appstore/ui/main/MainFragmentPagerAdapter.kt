package com.example.appstore.ui.main

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appstore.R

class MainFragmentPagerAdapter(
    private val fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val tabTitles: Array<String> =
        fragment.resources.getStringArray(R.array.main_tab_title)

    /**
     * Returns the number of pages
     */
    override fun getItemCount() = 2

    /**
     * This method will be invoked when a page is requested to create
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AlbumsFragment()
            else -> BookmarkFragment()
        }
    }

    fun getTabView(position: Int): View {
        val v = View.inflate(fragment.context, R.layout.custom_tab, null)
        v.findViewById<TextView>(R.id.tabTitleTv).text = tabTitles[position]
        return v
    }
}
