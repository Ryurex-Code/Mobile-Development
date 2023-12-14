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

    fun register(registerModel: RegisterModel): LiveData<Result<RegisterResponse>> {
        val resultLiveData = MutableLiveData<Result<RegisterResponse>>()

        if (registerModel.password == registerModel.repassword) {
            repository.register(RegisterRequest(
                username = registerModel.username,
                email = registerModel.email,
                password = registerModel.password
            )).observeForever { result ->
                resultLiveData.value = result
            }
        } else {
            resultLiveData.value = Result.Error("Password dan Konfirmasi Password tidak cocok")
        }

        return resultLiveData
    }
}