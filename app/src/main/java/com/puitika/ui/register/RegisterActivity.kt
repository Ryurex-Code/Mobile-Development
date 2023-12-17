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
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.puitika.utils.showToast
import androidx.activity.viewModels
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.puitika.R
import com.puitika.data.model.RegisterModel
import com.puitika.databinding.ActivityRegisterBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.login.LoginActivity
import com.puitika.utils.Result

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var factory: ViewModelFactory
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
            progressBar.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                progressBar.visibility = View.GONE
                finish()
            }, 1000)
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

            // Check if any field is empty
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                showToast(this, "All fields must be filled")
                if (username.isEmpty()) {
                    binding.etUsername.error = "Username is required"
                }
                if (email.isEmpty()) {
                    binding.etEmail.error = "Email is required"
                }
                if (password.isEmpty()) {
                    binding.etPassword.error = "Password is required"
                }
                if (repassword.isEmpty()) {
                    binding.etRePassword.error = "Repeat password is required"
                }
            } else if (password.length < 8) {
                // Check if password is at least 8 characters long
                showToast(this, "Password should be at least 8 characters long")
                binding.etPassword.error = "Password should be at least 8 characters long"
            } else if (password != repassword) {
                // Check if passwords match
                showToast(this, "Passwords do not match")
                binding.etRePassword.error = "Passwords do not match"
            } else if (username.contains(" ")) {
                // Check if username contains spaces
                showToast(this, "Username should not contain spaces")
                binding.etUsername.error = "Username should not contain spaces"
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                // Check if the email has a valid format
                showToast(this, "Invalid email format")
                binding.etEmail.error = "Invalid email format"
            } else {
                val registerModel = RegisterModel(
                    username = username,
                    email = email,
                    password = password,
                    repassword = repassword
                )
                viewModel.register(registerModel).observe(this) { result ->
                    when (result) {
                        is Result.Loading -> {}
                        is Result.Error -> {
                            showToast(this, result.data)
                            progressBar.visibility = View.GONE
                        }

                        is Result.Success -> {
                            progressBar.visibility = View.GONE
                            showSuccessDialog()

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
    }


    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }
    private fun showSuccessDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.fragment_popup_acccreated)

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.gravity = Gravity.CENTER_VERTICAL
        window?.attributes = layoutParams

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            finish()
        }, 1000)
    }
}