package com.puitika.ui.main.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.puitika.R
import com.puitika.databinding.ActivityMainBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.login.LoginActivity
import com.puitika.ui.main.event.EventFragment
import com.puitika.ui.main.home.HomeFragment
import com.puitika.ui.main.home.HomeViewModel
import com.puitika.ui.main.scan.ScanFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setViewModelFactory()

        viewModel.getSession().observe(this){
            if (!it.isLogin){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                setContentView(binding.root)
                setupBottomNav()
                supportFragmentManager.addOnBackStackChangedListener(backStackListener)
            }
        }
    }

    private fun setupBottomNav() {
        with(binding) {
            bottomNav.add(MeowBottomNavigation.Model(1, R.drawable.home_ic))
            bottomNav.add(MeowBottomNavigation.Model(2, R.drawable.scan_ic))
            bottomNav.add(MeowBottomNavigation.Model(3, R.drawable.event_ic))
            bottomNav.show(1)
            navigation(HomeFragment(), true)

            bottomNav.setOnClickMenuListener {
                when (it.id) {
                    1 -> navigation(HomeFragment())
                    2 -> navigation(ScanFragment())
                    3 -> navigation(EventFragment())
                }
            }
            bottomNav.setOnReselectListener {
                when(it.id){
                    2 -> navigation(ScanFragment())
                }
            }
        }
    }

    private fun navigation(fragment:Fragment, isFromLogin:Boolean = false){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        if (!isFromLogin)fragmentTransaction.addToBackStack(null)
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

    private val backStackListener = FragmentManager.OnBackStackChangedListener {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)

        when (currentFragment) {
            is HomeFragment -> binding.bottomNav.show(1, true)
            is ScanFragment -> binding.bottomNav.show(2, true)
            is EventFragment -> binding.bottomNav.show(3, true)
        }
    }

    override fun onDestroy() {
        // Remove the back stack listener to avoid memory leaks
        supportFragmentManager.removeOnBackStackChangedListener(backStackListener)
        super.onDestroy()
    }

    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }

    companion object {
        const val FROM_EVENT = "fromAddEvent"
    }
}
