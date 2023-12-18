package com.puitika.ui.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.puitika.R
import com.puitika.ui.login.LoginActivity
import com.puitika.ui.profile.ProfileEditActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val icWorldIcon = findViewById<RelativeLayout>(R.id.ic_world_icon)
        val tvChangeLang = findViewById<TextView>(R.id.tv_change_lang)

        icWorldIcon.setOnClickListener {
            openLanguageSettings()
        }

        tvChangeLang.setOnClickListener {
            openLanguageSettings()
        }
        logout()
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
            showLogoutConfirmationDialog()
        }
    }

    private fun openLanguageSettings() {
        val languageIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(languageIntent)
    }
    private fun showLogoutConfirmationDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.fragment_popup_confirmlogout)

        val progressBarLogout = dialog.findViewById<ProgressBar>(R.id.progress_bar)
        val btnNoLogout = dialog.findViewById<Button>(R.id.button_nologout)
        val btnYesLogout = dialog.findViewById<Button>(R.id.button_yeslogout)

        btnNoLogout.setOnClickListener {
            dialog.dismiss()
        }

        btnYesLogout.setOnClickListener {
            progressBarLogout.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                progressBarLogout.visibility = View.GONE

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()

                dialog.dismiss()
            }, 2000)
        }
        progressBarLogout.visibility = View.GONE

        dialog.show()
    }
}
