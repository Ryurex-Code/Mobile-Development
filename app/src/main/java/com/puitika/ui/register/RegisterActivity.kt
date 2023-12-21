package com.puitika.ui.register

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import com.puitika.utils.showToast
import androidx.activity.viewModels
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.puitika.R
import com.puitika.data.model.RegisterModel
import com.puitika.databinding.ActivityRegisterBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.login.LoginActivity
import com.puitika.utils.Result
import okhttp3.MediaType.Companion.toMediaType

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var factory: ViewModelFactory
    private var loadingPopup: PopupWindow? = null
    private lateinit var progressBar: ProgressBar
    private val viewModel: RegisterViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModelFactory()
        progressBar = findViewById(R.id.progress_bar)
        setAction()
    }

    private fun setAction() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val repassword = binding.etRePassword.text.toString()

            binding.etUsername.error = null
            binding.etEmail.error = null
            binding.etPassword.error = null
            binding.etRePassword.error = null

            val registerModel = RegisterModel(
                username = username,
                email = email,
                password = password,
                repassword = repassword
            )
            viewModel.register(registerModel).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        showLoadingDialog(true)
                    }

                    is Result.Error -> {
                        showLoadingDialog(false)
                        Handler(Looper.getMainLooper()).postDelayed({
                            progressBar.visibility = View.GONE
                            showCustomDialog(result.data, false)
                        }, 500)
                    }

                    is Result.Success -> {
                        showLoadingDialog(false)
                        showCustomDialog(result.data.message, true)
                        Handler(Looper.getMainLooper()).postDelayed({
                            if (!isFinishing) {
                                startActivity(Intent(this, LoginActivity::class.java))
                            }
                        }, 2000)
                    }

                }
            }
        }
    }


    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
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
}