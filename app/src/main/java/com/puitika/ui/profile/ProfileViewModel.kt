package com.puitika.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.puitika.data.pref.UserModel
import com.puitika.repository.PuitikaRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository:PuitikaRepository): ViewModel() {
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}