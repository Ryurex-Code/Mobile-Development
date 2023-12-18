package com.puitika.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puitika.data.model.RegisterModel
import com.puitika.data.remote.response.RegisterResponse
import com.puitika.data.request.RegisterRequest
import com.puitika.repository.PuitikaRepository
import com.puitika.utils.Result

class RegisterViewModel(private val repository: PuitikaRepository) : ViewModel() {

    private val resultLiveData = MutableLiveData<Result<RegisterResponse>>()

    fun register(registerModel: RegisterModel): LiveData<Result<RegisterResponse>> {
        if (registerModel.username.isEmpty() || registerModel.email.isEmpty() || registerModel.password.isEmpty() || registerModel.repassword.isEmpty()) {
            resultLiveData.value = Result.Error("All field should be filled!")
        } else {
            val usernameValid = isUsernameValid(registerModel.username)
            if (usernameValid) {
                val emailValid = isEmailValid(registerModel.email)
                if (emailValid) {
                    val confirmPasswordValid = isConfirmPasswordValid(registerModel.password, registerModel.repassword)
                    val passwordValid = isPasswordValid(registerModel.password)
                    if (passwordValid) {
                        if (confirmPasswordValid) {
                            repository.register(
                                RegisterRequest(
                                    username = registerModel.username,
                                    email = registerModel.email,
                                    password = registerModel.password
                                )
                            ).observeForever { result ->
                                resultLiveData.value = result
                            }
                        }
                    }
                }
            }
        }



        return resultLiveData
    }

    private fun isUsernameValid(username: String): Boolean {
        return if (username.isEmpty()) {
            resultLiveData.value = Result.Error("Username is required")
            false
        } else if (username.contains(" ")) {
            resultLiveData.value = Result.Error("Username should not contain spaces")
            false
        } else if (username.length < 5) {
            resultLiveData.value = Result.Error("Username should be at least 5 characters long")
            false
        }else {
            true
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return if (email.isEmpty()) {
            resultLiveData.value = Result.Error("Email is required")
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            resultLiveData.value = Result.Error("Invalid email format")
            false
        } else {
            true
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return if (password.isEmpty() || password.length < 8) {
            resultLiveData.value = Result.Error("Password should be at least 8 characters long")
            false
        } else {
            true
        }
    }

    private fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean {
        return if (confirmPassword.isEmpty() || password != confirmPassword) {
            resultLiveData.value = Result.Error("Passwords do not match")
            false
        } else {
            true
        }
    }
}
