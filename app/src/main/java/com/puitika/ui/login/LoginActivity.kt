package com.puitika.ui.login

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
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
            viewModel.login(loginModel).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        showProgressBar()
                    }

                    is Result.Success -> {
                        hideProgressBar()
                        showCustomDialog("You are logged in",true)
                        Handler(Looper.getMainLooper()).postDelayed({
                            startActivity(Intent(this, MainActivity::class.java).apply {
                                putExtra("fromLogin", true)
                            })
                        }, 2000)
                    }


                    is Result.Error -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            hideProgressBar()
                            showCustomDialog(result.data, false)
                        }, 1000)

                    }

                    else -> {}
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


    private fun showCustomDialog(message: String, success: Boolean) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(com.puitika.R.layout.fragment_popup_loggedin)

        // Assuming there's a TextView in your layout to display the message
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
