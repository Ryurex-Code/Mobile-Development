package com.puitika.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puitika.R
import com.puitika.databinding.ActivityLoginBinding
import com.puitika.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction(){
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnConfirm.setOnClickListener {
            finish()
        }
    }
}