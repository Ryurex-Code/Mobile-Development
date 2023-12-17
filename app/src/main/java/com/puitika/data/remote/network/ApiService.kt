package com.puitika.data.remote.network

import com.puitika.data.remote.response.ClothResponse
import com.puitika.data.remote.response.CreateEventResponse
import com.puitika.data.remote.response.EventResponse
import com.puitika.data.remote.response.LoginResponse
import com.puitika.data.remote.response.RegionResponse
import com.puitika.data.request.RegisterRequest
import com.puitika.data.remote.response.RegisterResponse
import com.puitika.data.request.CreateEventRequest
import com.puitika.data.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @POST("/register")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse

    @POST("/login")
    suspend fun login(@Body body: LoginRequest) : LoginResponse

    @GET("/region")
    suspend fun getRegion(): RegionResponse

    @GET("/cloth")
    suspend fun getCloth(): ClothResponse

    @GET("/events")
    suspend fun getEvent(): EventResponse

    @POST("/createEvent")
    suspend fun createEvent(@Body body : CreateEventRequest) : CreateEventResponse
}
