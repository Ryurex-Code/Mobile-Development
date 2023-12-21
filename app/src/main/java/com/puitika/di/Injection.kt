package com.puitika.di

import android.content.Context
import com.puitika.data.local.AccountPreference
import com.puitika.data.pref.UserPreference
import com.puitika.data.pref.dataStore
import com.puitika.data.remote.network.ApiConfig
import com.puitika.repository.PuitikaRepository

object Injection {
    fun provideRepository(context: Context): PuitikaRepository {
        val preferences = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return PuitikaRepository.getInstance(preferences, apiService)
    }
}