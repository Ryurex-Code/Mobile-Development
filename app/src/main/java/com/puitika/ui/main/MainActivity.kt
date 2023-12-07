package com.puitika.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.puitika.R
import com.puitika.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btmNav.add(MeowBottomNavigation.Model(1, R.drawable.event_ic))
            btmNav.add(MeowBottomNavigation.Model(2, R.drawable.scan_ic))
            btmNav.add(MeowBottomNavigation.Model(3, R.drawable.home_ic))
            btmNav.show(1)
        }
        navigation()

    }

    private fun navigation(){

    }
}