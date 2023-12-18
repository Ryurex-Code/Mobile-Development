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
                        progressBar.visibility = View.VISIBLE
                    }

                    is Result.Error -> {
                        progressBar.visibility = View.VISIBLE
                        Handler(Looper.getMainLooper()).postDelayed({
                            progressBar.visibility = View.GONE
                            showCustomDialog(result.data, false)
                        }, 500)
                    }

                    is Result.Success -> {
                        progressBar.visibility = View.GONE
                        showCustomDialog(result.data.message)
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

    private fun showCustomDialog(message: String, success: Boolean = true) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(com.puitika.R.layout.fragment_popup_loggedin)

        val messageTextView: TextView = dialog.findViewById(R.id.tv_loggedin)
        messageTextView.text = message
        val checkListImageView: ImageFilterView = dialog.findViewById(R.id.iv_checklist)
        val cancelImageView: ImageFilterView = dialog.findViewById(R.id.iv_cancel)

        if (success) {
            checkListImageView.visibility = View.VISIBLE
            cancelImageView.visibility = View.GONE
        } else {
            checkListImageView.visibility = View.GONE
            cancelImageView.visibility = View.VISIBLE
        }

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, 2000)
    }
}