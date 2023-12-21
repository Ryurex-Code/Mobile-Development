package com.puitika.ui.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.puitika.R
import com.puitika.databinding.ActivityMainBinding
import com.puitika.databinding.ActivityProfileBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.login.LoginActivity
import com.puitika.ui.main.main.MainViewModel
import com.puitika.ui.main.main.ProfileViewModel
import com.puitika.ui.profile.ProfileEditActivity
import com.puitika.utils.showToast
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var factory: ViewModelFactory
    private var loadingPopup: PopupWindow? = null
    private val viewModel: ProfileViewModel by viewModels { factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModelFactory()
        val icWorldIcon = findViewById<RelativeLayout>(R.id.ic_world_icon)
        val tvChangeLang = findViewById<TextView>(R.id.tv_change_lang)
        val changeLanguage = findViewById<TextView>(R.id.tv_change_theme)
        val favorite = findViewById<TextView>(R.id.tv_favorite)
        val image = findViewById<CircleImageView>(R.id.iv_profileimage)

        image.setOnClickListener{
            showToast(this, "Coming Soon!")
        }
        icWorldIcon.setOnClickListener {
            showToast(this, "Coming Soon!")
        }

        changeLanguage.setOnClickListener {
            showToast(this, "Coming Soon!")
        }

        tvChangeLang.setOnClickListener {
            showToast(this, "Coming Soon!")
        }

        favorite.setOnClickListener{
            showToast(this, "Coming Soon!")
        }


        logout()
        viewModel.getSession().observe(this) {
            binding.tvUsername.text = it.username
            binding.tvEmail.text = it.email
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
            dialog.dismiss()
            showLoadingDialog(true)

            Handler(Looper.getMainLooper()).postDelayed({

                showLoadingDialog(false)
                showCustomDialog("Successfully Logged Out!", true)

                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.logout()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()

                }, 2000)
            }, 2000)
        }
        progressBarLogout.visibility = View.GONE

        dialog.show()
    }

    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }

    private fun showLoadingDialog(play: Boolean = true) {
        if (play) {
            // Show loading popup
            if (loadingPopup == null) {
                val loadingView = layoutInflater.inflate(R.layout.scan_loading, null)
                loadingPopup = PopupWindow(
                    loadingView,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
                )
                loadingPopup?.setBackgroundDrawable(resources.getDrawable(android.R.color.transparent))
                loadingPopup?.isOutsideTouchable = false
                loadingPopup?.isFocusable = true
            }
            loadingPopup?.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
        } else {
            // Dismiss loading popup
            loadingPopup?.dismiss()
        }
    }

    private fun showCustomDialog(message: String, success: Boolean) {
        val popupView = layoutInflater.inflate(R.layout.fragment_popup_loggedin, null)

        // Find views in the custom layout
        val messageTextView: TextView = popupView.findViewById(R.id.tv_loggedin)
        val checkListImageView: ImageFilterView = popupView.findViewById(R.id.iv_checklist)
        val cancelImageView: ImageFilterView = popupView.findViewById(R.id.iv_cancel)

        // Set message and visibility based on success
        messageTextView.text = message
        if (success) {
            checkListImageView.visibility = View.VISIBLE
            cancelImageView.visibility = View.GONE
        } else {
            checkListImageView.visibility = View.GONE
            cancelImageView.visibility = View.VISIBLE
        }

        // Create a PopupWindow
        val popupWindow = PopupWindow(
            popupView,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        // Set background drawable with a transparent color
        popupWindow.setBackgroundDrawable(resources.getDrawable(android.R.color.transparent))

        // Show the PopupWindow at the center of the screen
        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        // Dismiss the PopupWindow after a delay
        Handler(Looper.getMainLooper()).postDelayed({
            popupWindow.dismiss()
        }, 2000)
    }
}
