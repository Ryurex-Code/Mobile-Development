package com.puitika.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.puitika.data.pref.UserModel
import com.puitika.data.pref.UserPreference
import com.puitika.data.remote.network.ApiConfig
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
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.lang.Exception

class PuitikaRepository(
    private val pref: UserPreference,
    private var apiService: ApiService
) {

    fun register(body: RegisterRequest): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            apiService = ApiConfig.getApiService()
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
            apiService = ApiConfig.getApiService()
            val loginRes = apiService.login(body)
            if (loginRes.status == "success") {
                try {
                    val bioRes = apiService.getBiodata(loginRes.apikey)
                    pref.saveSession(UserModel(email = bioRes.email, username = bioRes.username, api = loginRes.apikey))
                }catch (e:Exception) {
                    Log.e("/me", e.message.toString())
                }
                emit(Result.Success(loginRes))
            }
            else emit(Result.Error(loginRes.message))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getSession(): Flow<UserModel> {
        return pref.getSession()
    }

    suspend fun logout() {
        pref.logout()
    }

    fun getRegions(): LiveData<Result<RegionResponse>> = liveData {
        emit(Result.Loading)
        try {
            apiService = ApiConfig.getApiService()
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
            apiService = ApiConfig.getApiService()
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
            apiService = ApiConfig.getApiService()
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
            apiService = ApiConfig.getApiService()
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
            pref: UserPreference,
            apiService: ApiService
        ): PuitikaRepository =
            instance ?: synchronized(this) {
                instance ?: PuitikaRepository(pref, apiService)
            }.also { instance = it }
    }
}

