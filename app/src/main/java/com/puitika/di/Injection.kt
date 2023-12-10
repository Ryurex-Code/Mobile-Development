package com.puitika.di

import android.content.Context
import com.puitika.data.local.AccountPreference
import com.puitika.data.remote.network.ApiConfig
import com.puitika.repository.PuitikaRepository

object Injection {
    fun provideRepository(context: Context): PuitikaRepository {
        val preferences = AccountPreference(context)
        val apiService = ApiConfig.getApiService()
        return PuitikaRepository.getInstance(preferences, apiService)
    }
}