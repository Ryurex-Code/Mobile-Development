package com.puitika.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.puitika.R
import com.puitika.data.model.LoginModel
import com.puitika.databinding.ActivityLoginBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.main.main.MainActivity
import com.puitika.ui.register.RegisterActivity
import com.puitika.utils.Result
import com.puitika.utils.showToast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var loadingPopup: PopupWindow? = null
    private lateinit var factory: ViewModelFactory
    private val viewModel: LoginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModelFactory()
        setAction()
    }

    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }

    private fun setAction() {
        binding.btnLogin.setOnClickListener {
            val loginModel = LoginModel(
                username = binding.etUsername.text.toString(),
                password = binding.etPassword.text.toString()
            )
            viewModel.login(loginModel).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        showLoadingDialog(true)
                    }

                    is Result.Success -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            showLoadingDialog(false)
                            showCustomDialog("You are logged in", true)
                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("fromLogin", true)
                                startActivity(intent)
                            }, 2000)
                        }, 1000)
                    }


                    is Result.Error -> {
                        showLoadingDialog(false)
                        showCustomDialog(result.data, false)
                    }

                    else -> {}
                }
            }
        }

        binding.apply {
            tvForgotPassword.setOnClickListener {
                showToast(this@LoginActivity, "Coming Soon!")
            }
            tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
            ivGoogle.setOnClickListener {
                showToast(this@LoginActivity, "Coming Soon!")
            }
            ivX.setOnClickListener {
                showToast(this@LoginActivity, "Coming Soon!")
            }
            ivFacebook.setOnClickListener {
                showToast(this@LoginActivity, "Coming Soon!")
            }
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
