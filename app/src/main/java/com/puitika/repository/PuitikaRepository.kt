package com.puitika.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.puitika.data.dummy.Event
import com.puitika.data.local.AccountPreference
import com.puitika.data.remote.network.ApiConfig2
import com.puitika.data.request.RegisterRequest
import com.puitika.data.remote.network.ApiService
import com.puitika.data.remote.response.ClothResponse
import com.puitika.data.remote.response.CreateEventResponse
import com.puitika.data.remote.response.EventResponse
import com.puitika.data.remote.response.LoginResponse
import com.puitika.data.remote.response.RegionResponse
import com.puitika.data.remote.response.RegisterResponse
import com.puitika.data.remote.response.ScanResponse
import com.puitika.data.request.CreateEventRequest
import com.puitika.data.request.LoginRequest
import com.puitika.utils.Result
import okhttp3.MultipartBody
import java.lang.Exception

class PuitikaRepository(
    private val preference: AccountPreference,
    private var apiService: ApiService
) {

    fun register(body: RegisterRequest): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val res = apiService.register(body)
            if (res.status == "success") emit(Result.Success(res))
            else emit(Result.Error(res.message))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun login(body: LoginRequest): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val res = apiService.login(body)
            if (res.status == "success") emit(Result.Success(res))
            else emit(Result.Error(res.message))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getRegions(): LiveData<Result<RegionResponse>> = liveData {
        emit(Result.Loading)
        try {
            val res = apiService.getRegion()
            if(!res.error) emit(Result.Success(res))
            else emit(Result.Error(res.status))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getClothes(): LiveData<Result<ClothResponse>> = liveData {
        emit(Result.Loading)
        try {
            val res = apiService.getCloth()
            if(!res.error) emit(Result.Success(res))
            else emit(Result.Error(res.status))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getEvents(): LiveData<Result<EventResponse>> = liveData {
        emit(Result.Loading)
        try {
            val res = apiService.getEvent()
            if(!res.error) emit(Result.Success(res))
            else emit(Result.Error(res.status))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun createEvent(body: CreateEventRequest): LiveData<Result<CreateEventResponse>> = liveData {
        emit(Result.Loading)
        try {
            val res = apiService.createEvent(body)
            if (res.status == "success") emit(Result.Success(res))
            else emit(Result.Error(res.message))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun scanCloth(file: MultipartBody.Part): LiveData<Result<ScanResponse>> = liveData {
        emit(Result.Loading)
        try {
            apiService = ApiConfig2.getApiService()
            val res = apiService.scanCloth(file)
            if (!res.error) emit(Result.Success(res))
            else emit(Result.Error(res.message))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
    companion object {
        @Volatile
        private var instance: PuitikaRepository? = null
        fun getInstance(
            preferences: AccountPreference,
            apiService: ApiService
        ): PuitikaRepository =
            instance ?: synchronized(this) {
                instance ?: PuitikaRepository(preferences, apiService)
            }.also { instance = it }
    }
}

