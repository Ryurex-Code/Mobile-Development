package com.puitika.ui.main.main

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.puitika.R
import com.puitika.databinding.ActivityMainBinding
import com.puitika.databinding.FragmentPopupBinding
import com.puitika.databinding.FragmentScanBinding
import com.puitika.ui.login.LoginActivity
import com.puitika.ui.main.event.AddEventFormActivity
import com.puitika.ui.main.event.EventFragment
import com.puitika.ui.main.home.HomeFragment
import com.puitika.ui.main.scan.ScanFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingPopup: FragmentPopupBinding
    private lateinit var bindingScan: FragmentScanBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoggedIn = intent.getBooleanExtra(EXTRA_USER, false)

        if (!isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
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

    private fun navigation(fragment: Fragment, isFromLogin:Boolean = false, isToScan:Boolean = false) {
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

    companion object {
        const val EXTRA_USER = "fromLogin"
    }
}
