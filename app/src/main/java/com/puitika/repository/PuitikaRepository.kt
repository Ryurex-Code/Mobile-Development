package com.puitika.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.puitika.data.dummy.Cloth
import com.puitika.data.dummy.Region
import com.puitika.data.dummy.dummyTraditionalCloths
import com.puitika.data.dummy.regionList
import com.puitika.data.local.AccountPreference
import com.puitika.data.request.RegisterRequest
import com.puitika.data.remote.network.ApiService
import com.puitika.data.remote.response.RegisterResponse
import com.puitika.utils.Result
import java.lang.Exception

class PuitikaRepository(
    private val preference: AccountPreference,
    private val apiService: ApiService
) {

    fun register(body : RegisterRequest) : LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val res = apiService.register(body)
            if (res.status == "success") emit(Result.Success(res))
            else emit(Result.Error(res.message))
        }catch (e : Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getRegions(): LiveData<Result<Region>> = liveData {
        emit(Result.Loading)
        try {
            if (regionList.error){
                emit(Result.Error(regionList.message))
            }else{
                emit(Result.Success(regionList))
            }
        }catch (e:Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getClothes(): LiveData<Result<Cloth>> = liveData {
        emit(Result.Loading)
        try {
            if (dummyTraditionalCloths.error){
                emit(Result.Error(dummyTraditionalCloths.message))
            }else{
                emit(Result.Success(dummyTraditionalCloths))
            }
        }catch (e:Exception){
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