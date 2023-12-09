package com.puitika.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.puitika.R
import com.puitika.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isLoggedIn = intent.getBooleanExtra(EXTRA_USER,false)

        if (!isLoggedIn){
            startActivity(Intent(this,LoginActivity::class.java))
        }

        with(binding) {
            bottomNav.add(MeowBottomNavigation.Model(1, R.drawable.home_ic))
            bottomNav.add(MeowBottomNavigation.Model(2, R.drawable.scan_ic))
            bottomNav.add(MeowBottomNavigation.Model(3, R.drawable.event_ic))
            bottomNav.show(1)
            navigation(HomeFragment(),true)

            bottomNav.setOnClickMenuListener {
                when (it.id) {
                    1 -> navigation(HomeFragment(),false)
                    2 -> navigation(ScanFragment(),false)
                    3 -> navigation(EventFragment(),false)
                }
            }
        }

        supportFragmentManager.addOnBackStackChangedListener(onBackStackChangedListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.removeOnBackStackChangedListener(onBackStackChangedListener)
    }

    private val onBackStackChangedListener =
        FragmentManager.OnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
            currentFragment?.let { updateBottomNavigation(it) }
        }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun navigation(fragment: Fragment, first:Boolean) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        if (!first){
        fragmentTransaction.addToBackStack(null)}
        fragmentTransaction.commit()
        updateBottomNavigation(fragment)
    }

    private fun updateBottomNavigation(fragment: Fragment) {
        val selectedItem = when (fragment) {
            is HomeFragment -> 1
            is ScanFragment -> 2
            is EventFragment -> 3
            else -> return
        }
        binding.bottomNav.show(selectedItem, true)
    }

    companion object {
        const val EXTRA_USER = "fromLogin"

    }
}


