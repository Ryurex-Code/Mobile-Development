package com.puitika.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puitika.data.model.LoginModel
import com.puitika.data.model.RegisterModel
import com.puitika.data.remote.response.LoginResponse
import com.puitika.data.remote.response.RegisterResponse
import com.puitika.data.request.LoginRequest
import com.puitika.data.request.RegisterRequest
import com.puitika.repository.PuitikaRepository
import com.puitika.utils.Result

class LoginViewModel(private val repository: PuitikaRepository) : ViewModel() {
    fun login(loginModel: LoginModel): LiveData<Result<LoginResponse>> {
        return if (loginModel.username.isEmpty() || loginModel.password.isEmpty()) {
            MutableLiveData<Result<LoginResponse>>().apply {
                value = Result.Error("Username and password are required.")
            }
        } else {
            repository.login(
                LoginRequest(
                    username = loginModel.username,
                    password = loginModel.password
                )
            )
        }
    }
}


