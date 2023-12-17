package com.puitika.ui.login

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import com.puitika.data.model.LoginModel
import com.puitika.databinding.ActivityLoginBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.main.main.MainActivity
import com.puitika.ui.register.RegisterActivity
import com.puitika.utils.Result
import com.puitika.utils.showToast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
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
            showProgressBar()

            val loginModel = LoginModel(
                username = binding.etUsername.text.toString(),
                password = binding.etPassword.text.toString()
            )
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.login(loginModel).observe(this) { result ->
                    hideProgressBar()
                    when (result) {
                        is Result.Success -> {
                            showSuccessDialog()
                            startActivity(Intent(this, MainActivity::class.java).apply {
                                putExtra("fromLogin", true)
                            })
                        }


                        is Result.Error -> {
                            showToast(this, result.data)
                        }

                        else -> {}
                    }
                }
            }, 1000)
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            hideProgressBar()
        }, 1000)
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


    private fun showSuccessDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(com.puitika.R.layout.fragment_popup_loggedin)

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, 1000)
    }
}
