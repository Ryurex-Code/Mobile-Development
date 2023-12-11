package com.puitika.data.remote.network

import com.puitika.data.request.RegisterRequest
import com.puitika.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("/register")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse
}
