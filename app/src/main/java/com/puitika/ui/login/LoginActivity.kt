package com.puitika.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val loginModel = LoginModel(
                username = binding.etUsername.text.toString(),
                password = binding.etPassword.text.toString()
            )
            viewModel.login(loginModel).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Error -> {
                        showToast(this, result.data)
                    }

                    is Result.Success -> {
                        showToast(this, result.data.message)
                        startActivity(Intent(this, MainActivity::class.java))
                        intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("fromLogin", true)
                        startActivity(intent)
                    }
                }
            }
        }
        binding.tvRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}