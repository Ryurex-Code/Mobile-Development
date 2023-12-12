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
        setAction()

    }

    private fun setAction(){
        binding.btnLogin.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fromLogin", true)
            startActivity(intent)
        }
        binding.tvRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}