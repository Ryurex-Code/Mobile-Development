package com.puitika.ui.main.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.puitika.R
import com.puitika.databinding.ActivityMainBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.login.LoginActivity
import com.puitika.ui.main.event.EventFragment
import com.puitika.ui.main.home.HomeFragment
import com.puitika.ui.main.scan.ScanFragment
import com.puitika.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { factory }

    private var backPressedTime: Long = 0
    private val BACK_PRESS_INTERVAL = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setViewModelFactory()

        viewModel.getSession().observe(this) {
            if (!it.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                val fromPage = intent.getIntExtra(FROM_PAGE, 1)
                Log.wtf("AHAHAHAA", fromPage.toString())
                setContentView(binding.root)
                setupBottomNav(fromPage)
            }
        }
    }

    private fun setupBottomNav(navigation: Int = 1) {
        with(binding) {
            bottomNav.add(MeowBottomNavigation.Model(1, R.drawable.home_ic))
            bottomNav.add(MeowBottomNavigation.Model(2, R.drawable.scan_ic))
            bottomNav.add(MeowBottomNavigation.Model(3, R.drawable.event_ic))
            if (navigation == 1) {
                bottomNav.show(1)
                navigation(HomeFragment(), true)
            } else if (navigation == 2) {
                bottomNav.show(2)
                navigation(ScanFragment(), true)
            } else {
                bottomNav.show(3)
                navigation(EventFragment(), true)
            }

            bottomNav.setOnClickMenuListener {
                when (it.id) {
                    1 -> navigation(HomeFragment())
                    2 -> navigation(ScanFragment())
                    3 -> navigation(EventFragment())
                }
            }
            bottomNav.setOnReselectListener {
                when (it.id) {
                    2 -> navigation(ScanFragment())
                }
            }
        }
    }

    private fun navigation(fragment: Fragment, isFromLogin: Boolean = false) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        if (!isFromLogin) fragmentTransaction.addToBackStack(null)
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

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)

        when (currentFragment) {
            is HomeFragment -> handleBackPressedForHome()
            is ScanFragment -> navigateToHomeFragment()
            is EventFragment -> navigateToHomeFragment()
            else -> {
                // For other fragments, allow the default back button behavior
                super.onBackPressed()
            }
        }
    }

    private fun handleBackPressedForHome() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - backPressedTime > BACK_PRESS_INTERVAL) {
            // First back press
            backPressedTime = currentTime
            showToast(this,"Press back again to exit")
        } else {
            // Second back press within the interval, exit the app
            super.onBackPressed()
        }
    }

    private fun navigateToHomeFragment() {
        // Navigate back to HomeFragment
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        updateBottomNavigation(HomeFragment())
    }

    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }

    companion object {
        const val FROM_PAGE = "from_page"
    }
}
