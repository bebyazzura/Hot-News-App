package com.bebyaww.hotnewsapp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bebyaww.hotnewsapp.adapter.SectionPagerAdapter
import com.bebyaww.hotnewsapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
       _binding = ActivityMainBinding.inflate(layoutInflater)
       setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

       setupViewPager()
    }

    private fun setupViewPager(){
        binding.vpNews.adapter = SectionPagerAdapter(this)
        val tabList = arrayOf(
            "Common",
            "About Al Quran",
            "Aljazeera",
            "Warn for Muslim"
        )

        TabLayoutMediator(binding.tabs, binding.vpNews){ tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        // for getting the search view and set the searchable config
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.option_search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


