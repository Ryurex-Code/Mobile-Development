package com.puitika.data.remote.network

import com.puitika.data.remote.response.ClothResponse
import com.puitika.data.remote.response.LoginResponse
import com.puitika.data.remote.response.RegionResponse
import com.puitika.data.request.RegisterRequest
import com.puitika.data.remote.response.RegisterResponse
import com.puitika.data.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @POST("/register")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse

    @POST("/login")
    suspend fun login(@Body body: LoginRequest) : LoginResponse
}
