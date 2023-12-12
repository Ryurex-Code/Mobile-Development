package com.puitika.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    private fun setAction(){
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnConfirm.setOnClickListener {
            val registerModel = RegisterModel(
                username = binding.etUsername.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString(),
                repassword = binding.etRePassword.text.toString()
            )
            viewModel.register(registerModel).observe(this){result->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Error -> {
                        showToast(result.data)
                    }

                    is Result.Success -> {
                        showToast(result.data.message)
                        finish()
                    }
                }
            }
        }
    }

    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}