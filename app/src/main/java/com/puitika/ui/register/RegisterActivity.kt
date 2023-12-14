package com.puitika.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puitika.utils.showToast
import androidx.activity.viewModels
import com.puitika.data.model.RegisterModel
import com.puitika.databinding.ActivityRegisterBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.utils.Result

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: RegisterViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModelFactory()
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

            // Clear previous error messages
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
                        }

                        is Result.Success -> {
                            showToast(this, result.data.message)
                            finish()
                        }
                    }
                }
            }
        }
    }


    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }
}
