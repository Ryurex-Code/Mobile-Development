package com.puitika.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.puitika.R
import com.puitika.ui.login.LoginActivity
import com.puitika.ui.profile.ProfileEditActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Find the RelativeLayout and TextView by their IDs
        val icWorldIcon = findViewById<RelativeLayout>(R.id.ic_world_icon)
        val tvChangeLang = findViewById<TextView>(R.id.tv_change_lang)

        // Set an OnClickListener for the RelativeLayout
        icWorldIcon.setOnClickListener {
            // Handle click on the RelativeLayout
            openLanguageSettings()
        }

        // Set an OnClickListener for the TextView
        tvChangeLang.setOnClickListener {
            // Handle click on the TextView
            openLanguageSettings()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_edit -> {
                val intent = Intent(this, ProfileEditActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        val tvLogOut = findViewById<TextView>(R.id.tv_log_out)
        tvLogOut.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openLanguageSettings() {
        // Intent untuk membuka pengaturan bahasa di aplikasi Pengaturan (Settings)
        val languageIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(languageIntent)
    }
}
