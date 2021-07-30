package com.newsapp.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.newsapp.OnItemClickListener
import com.newsapp.R
import com.newsapp.data.NewsArticleDb
import com.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val adapter = ViewPagerAdapter(
            supportFragmentManager,
            binding.appBarMain.tabLayout.tabCount
        )
        binding.appBarMain.contentMain.viewPager.adapter = adapter

        binding.appBarMain.contentMain.viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.appBarMain.tabLayout
            )
        )
        binding.appBarMain.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.appBarMain.contentMain.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        binding.appBarMain.toolbar.setNavigationIcon(R.drawable.ic_lines_foreground);
        setSupportActionBar(binding.appBarMain.toolbar)

    }

    override fun onItemClick(newsArticleDb: NewsArticleDb) {
        navController.navigate(R.id.action_nav_home_to_detailsActivity);
    }
}
