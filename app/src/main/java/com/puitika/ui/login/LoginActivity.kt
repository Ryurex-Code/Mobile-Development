package com.puitika.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puitika.R
import com.puitika.databinding.ActivityLoginBinding
import com.puitika.databinding.ActivityMainBinding
import com.puitika.ui.main.MainActivity
import com.puitika.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

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