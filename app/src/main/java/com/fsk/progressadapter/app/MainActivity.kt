package com.fsk.progressadapter.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fsk.progressadapter.app.emptyitemdecoration.EmptyItemDecorationFragment
import com.fsk.progressadapter.app.progress.ProgressAdapterFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.tabs
import kotlinx.android.synthetic.main.activity_main.viewpager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager.adapter = PagerAdapter(2)

        TabLayoutMediator(tabs, viewpager) { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.progress_adapter)
                1 -> tab.setText(R.string.empty_item_decoration)
            }
        }.attach()

    }

    inner class PagerAdapter(
        private val tabCount: Int
    ) : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = tabCount

        override fun createFragment(position: Int): Fragment =
            when (position) {
                0 -> ProgressAdapterFragment()
                1 -> EmptyItemDecorationFragment()
                else -> throw IllegalStateException("Unknown position")
            }
    }
}